package com.wangbei.service.storage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangbei.ueditor.ActionEnter;
import com.wangbei.ueditor.PathFormat;
import com.wangbei.ueditor.define.AppInfo;
import com.wangbei.ueditor.define.BaseState;
import com.wangbei.ueditor.define.FileType;
import com.wangbei.ueditor.define.State;
import com.wangbei.ueditor.upload.StorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystemStorageService {
    @Autowired
    protected RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(getClass());
    ThreadLocal<MultipartFile> upfileLocal = new ThreadLocal<>();
    private Path rootLocation;
    @Value("${custom.outer.resources}")
    private String outerResources;
    @Value("${custom.outer.uploadremote}")
    private String uploadremote;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public void store(MultipartFile file) {
        ReadableByteChannel src = null;
        WritableByteChannel desc = null;
        try {
            if (file.isEmpty()) {
                throw new StorageException("不能保存空文件!" + file.getOriginalFilename());
            }
            InputStream is = file.getInputStream();
            OutputStream os = new FileOutputStream(rootLocation.resolve(file.getOriginalFilename()).toFile());
            src = Channels.newChannel(is);
            desc = Channels.newChannel(os);

            copy(src, desc);
        } catch (IOException e) {
            throw new StorageException("保存文件失败!" + file.getOriginalFilename(), e);
        } finally {
            if (src != null) {
                try {
                    src.close();
                } catch (IOException e) {
                }
            }
            if (desc != null) {
                try {
                    desc.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public void store(MultipartFile file, String remote) {
        ReadableByteChannel src = null;
        WritableByteChannel desc = null;
        try {
            if (file.isEmpty()) {
                throw new StorageException("不能保存空文件!" + file.getOriginalFilename());
            }
            String savePath = "/editorupload/{yyyy}{mm}{dd}/filename";
            String originFileName = file.getOriginalFilename();
            String suffix = FileType.getSuffixByFilename(originFileName);

            originFileName = originFileName.substring(0,
                    originFileName.length() - suffix.length());
            savePath = savePath + suffix;
            savePath = PathFormat.parse(savePath, originFileName);
            savePath=  savePath.replace("filename", originFileName);
            logger.info("savePath:{}",savePath);
            logger.info("保存的文件:{}",file.getOriginalFilename());
            InputStream is = file.getInputStream();
            State storageState = StorageManager.saveFileByInputStream(is,
                    remote+savePath, 2048000);
            if (storageState.isSuccess()) {
                logger.info("上传成功");
            }
            is.close();
        } catch (IOException e) {
            throw new StorageException("保存文件失败!" + file.getOriginalFilename(), e);
        } finally {
            if (src != null) {
                try {
                    src.close();
                } catch (IOException e) {
                }
            }
            if (desc != null) {
                try {
                    desc.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private void copy(ReadableByteChannel src, WritableByteChannel desc) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16 * 1024);
        while (src.read(buffer) != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                desc.write(buffer);
            }
            buffer.clear();
        }
    }

    public void uploadAttachment(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        try {
            servletRequest.setCharacterEncoding("utf-8");
            servletResponse.setHeader("Content-Type", "text/html");
            File file = new File(outerResources + "editorupload");
            String rootPath = file.getParentFile().getAbsolutePath();
            // String rootPath = servletRequest.getServletContext().getRealPath("/");
            String result = new ActionEnter(servletRequest, rootPath).exec().toString();
            logger.info("result:{}", result);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(result);
            if (node.get("url") != null) {
                logger.info(outerResources + node.get("url").asText());
                upload(outerResources + node.get("url").asText());
            }
            servletResponse.getWriter().write(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String upload(String url) {
        File file = new File(url);
        FileSystemResource resource = new FileSystemResource(file);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("file", resource);
//        param.add("fileName",file.getName());
        String result = restTemplate.postForObject(uploadremote, param, String.class);
        logger.info("远程上传结果:{}", result);
        return "You successfully uploaded " + file.getName() + "!";
    }

}
