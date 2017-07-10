package com.wangbei.service.storage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService {

	private final Path rootLocation;

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

}
