package com.wangbei.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.wangbei.dao.SutraDao;
import com.wangbei.dao.UserFavouriteDao;
import com.wangbei.entity.Sutra;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.wxpay.api.HttpService;
import com.wangbei.util.HtmlUtil;
import com.wangbei.util.PinyinUtil;
import com.wangbei.util.enums.FavouriteTypeEnum;
import com.wangbei.util.enums.SutraTypeEnum;

/**
 * 经书 Service
 * 
 * @author luomengan
 *
 */
@Service
public class SutraService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${custom.outer.resources}")
	private String outerResources;

	@Autowired
	private SutraDao sutraDao;

	@Autowired
	private UserFavouriteDao userFavouriteDao;

	public Sutra getSutraInfo(Integer id) {
		Sutra result = sutraDao.retrieveSutraById(id);
		if (result != null) {
			checkIsFavourite(result);
		}
		return result;
	}

	@Transactional
	public Sutra addSutra(Sutra sutra) {
		return sutraDao.createSutra(sutra);
	}

	@Transactional
	public Sutra adminAddSutra(Sutra sutra) {
		String link = sutra.getLink();
		if (link != null && link.indexOf("<img src=") >= 0) {
			// 表示当前为富文件编辑器添加的，对link进行处理
			int imgIndex = link.indexOf("<img src=");
			int titleIndex = link.indexOf("title=");

			if (imgIndex > 0 && titleIndex > 0) {
				link = link.substring(imgIndex + 10, titleIndex - 2);
				sutra.setLink(link);
				// link = link.substring(link.indexOf("buddha") + 6);
				// sutra.setLink("http:" + link);
			}
		}

		String contentLink = sutra.getContentLink();
		if (contentLink != null && contentLink.indexOf("href=") >= 0) {
			// 表示当前为富文件编辑器添加的，对link进行处理
			int hrefIndex = contentLink.indexOf("href=");
			int titleIndex = contentLink.indexOf("title=");

			if (hrefIndex > 0 && titleIndex > 0) {
				contentLink = contentLink.substring(hrefIndex + 6, titleIndex - 2);
				sutra.setContentLink(contentLink);
				// contentLink =
				// contentLink.substring(contentLink.indexOf("buddha") + 6);
				// sutra.setContentLink("http:" + contentLink);
			}
		}

		return sutraDao.createSutra(sutra);
	}

	@Transactional
	public Sutra modifySutra(Sutra sutra) {
		String link = sutra.getLink();
		if (link != null && link.indexOf("<img src=") >= 0) {
			// 表示当前为富文件编辑器添加的，对link进行处理
			int imgIndex = link.indexOf("<img src=");
			int titleIndex = link.indexOf("title=");

			if (imgIndex > 0 && titleIndex > 0) {
				link = link.substring(imgIndex + 10, titleIndex - 2);
				sutra.setLink(link);
				// link = link.substring(link.indexOf("buddha") + 6);
				// sutra.setLink("http:" + link);
			}
		}

		String contentLink = sutra.getContentLink();
		if (contentLink != null && contentLink.indexOf("href=") >= 0) {
			// 表示当前为富文件编辑器添加的，对link进行处理
			int hrefIndex = contentLink.indexOf("href=");
			int titleIndex = contentLink.indexOf("title=");

			if (hrefIndex > 0 && titleIndex > 0) {
				contentLink = contentLink.substring(hrefIndex + 6, titleIndex - 2);
				sutra.setContentLink(contentLink);
				// contentLink =
				// contentLink.substring(contentLink.indexOf("buddha") + 6);
				// sutra.setContentLink("http:" + contentLink);
			}
		}
		return sutraDao.updateSutra(sutra);

		/*
		 * Sutra oldSutra = sutraDao.retrieveSutraById(sutra.getId()); String
		 * contentLink = oldSutra.getContentLink(); File file = new
		 * File(outerResources + contentLink.substring(1)); try {
		 * FileUtils.writeStringToFile(file, sutra.getContentLink(), "UTF-8");
		 * sutra.setContentLink(oldSutra.getContentLink()); return
		 * sutraDao.updateSutra(sutra); } catch (IOException e) { throw new
		 * RuntimeException("经文保存文件失败!"); }
		 */
	}

	@Transactional
	public void deleteSutra(Integer id) {
		sutraDao.deleteSutraById(id);
	}

	public Page<Sutra> sutras(int page, int limit) {
		return sutraDao.pageSutra(page, limit);
	}

	public Page<Sutra> sutrasByIsEnableOrderById(boolean isEnable, int page, int limit) {
		Page<Sutra> result = sutraDao.pageSutraByIsEnableOrderById(isEnable, page, limit);
		if (result.getContent() != null && result.getContent().size() > 0) {
			checkIsFavourite(result.getContent());
		}
		return result;
	}

	public Page<Sutra> sutrasByTypeAndIsEnableOrderById(SutraTypeEnum type, boolean isEnable, int page, int limit) {
		Page<Sutra> result = sutraDao.pageSutraByTypeAndIsEnableOrderById(isEnable, type, page, limit);
		if (result.getContent() != null && result.getContent().size() > 0) {
			checkIsFavourite(result.getContent());
		}
		return result;
	}

	public List<Sutra> list() {
		return sutraDao.listSutra();
	}

	public List<Sutra> listOrderById() {
		List<Sutra> result = sutraDao.listSutraOrderById();
		if (result != null && result.size() > 0) {
			checkIsFavourite(result);
		}
		return result;
	}

	public List<Sutra> listByTypeOrderById(SutraTypeEnum type) {
		List<Sutra> result = sutraDao.listSutraByTypeOrderById(type);
		if (result != null && result.size() > 0) {
			checkIsFavourite(result);
		}
		return result;
	}

	public List<Sutra> listByIsEnableOrderById(boolean isEnable) {
		return sutraDao.listSutraByIsEnableOrderById(isEnable);
	}

	private void checkIsFavourite(Sutra sutra) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		int userId = authUserDetails == null ? 0 : authUserDetails.getUserId();
		if (userId > 0) {
			List<Integer> resourceIdList = userFavouriteDao.getUserFavouriteResourceIdsByType(userId,
					FavouriteTypeEnum.SUTRA);
			if (resourceIdList != null && resourceIdList.size() > 0) {
				if (resourceIdList.contains(sutra.getId())) {
					sutra.setFavourite(true);
				}
			}
		}
	}

	private void checkIsFavourite(List<Sutra> list) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		int userId = authUserDetails == null ? 0 : authUserDetails.getUserId();
		if (userId > 0) {
			List<Integer> resourceIdList = userFavouriteDao.getUserFavouriteResourceIdsByType(userId,
					FavouriteTypeEnum.SUTRA);
			if (resourceIdList != null && resourceIdList.size() > 0) {
				for (Sutra sutra : list) {
					if (resourceIdList.contains(sutra.getId())) {
						sutra.setFavourite(true);
					}
				}
			}
		}
	}

	public void collectSutraData(int collectType, String resource, SutraTypeEnum type) {
		if (collectType == 1) {
			collectLocalData(resource);
		} else if (collectType == 2) {
			collectLiaotuoData();
		} else if (collectType == 3) {
			try {
				collectLocalData2(resource, type);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private File[] filterFile(File parentFile, final String suffix) {
		return parentFile.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.toLowerCase().endsWith(suffix)) {
					return true;
				}
				return false;
			}
		});
	}

	private File filterFile(File parentFile, final String suffix, final String prefix) {
		File[] fileArr = parentFile.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.toLowerCase().endsWith(suffix)) {
					String originPrefix = name.substring(0, name.indexOf(suffix));
					if (originPrefix.equals(prefix) || originPrefix.equals(prefix.replaceAll(" ", "-"))) {
						return true;
					}
					return false;
				}
				return false;
			}
		});
		if (fileArr != null && fileArr.length > 0) {
			return fileArr[0];
		}
		return null;
	}

	private File renameFile(File oldFile, String pyName) throws IOException {
		String name = oldFile.getName();
		if (!name.equals(pyName)) {
			File newDir = new File(oldFile.getParentFile(), pyName);
			FileUtils.moveFile(oldFile, newDir);
			return newDir;
		} else {
			return oldFile;
		}
	}

	private void collectLocalData2(String dir, SutraTypeEnum type) throws Exception {
		String sutraDirStr = outerResources + "document/sutra";
		if (dir != null && !"".equals(dir.trim())) {
			sutraDirStr = dir;
		}
		String httpBase = "http://10.0.0.4:8080/buddha/document/sutra/";
		File sutraDir = new File(sutraDirStr);

		File[] txtFile = filterFile(sutraDir, ".txt");
		for (File txt : txtFile) {
			Sutra sutra = new Sutra();
			String name = txt.getName();
			String pyName = PinyinUtil.getPingYin(name, true);
			String jpgPyName = "";
			String sutraName = name.substring(0, name.lastIndexOf("."));
			File jpg = filterFile(sutraDir, ".jpg", sutraName);
			if(jpg != null) {
				jpgPyName = PinyinUtil.getPingYin(jpg.getName(), true);
				jpg = renameFile(jpg, jpgPyName);
			} else {
				continue;
			}
			txt = renameFile(txt, pyName);
			
			sutra.setContentLink(httpBase + pyName);
			sutra.setIsEnable(true);
			sutra.setTitle(sutraName);
			sutra.setType(type);
			sutra.setLink(httpBase + jpgPyName);
			sutraDao.createSutra(sutra);
		}
	}

	private void collectLocalData(String resource) {
		File dir = new File(resource);
		Map<String, String> fileNameMap = new HashMap<>();
		for (String fileName : dir.list()) {
			String[] fileNameArr = fileName.split("\\.");
			fileNameMap.put(fileName, fileNameArr[1]);
		}

		for (Map.Entry<String, String> entry : fileNameMap.entrySet()) {
			if ("txt".equals(entry.getValue())) {
				String documentFileName = entry.getKey();
				String splitFileName = documentFileName.split("\\.")[0];

				boolean isMatchPic = false;
				for (Map.Entry<String, String> innerEntry : fileNameMap.entrySet()) {
					String innerSplitFileName = innerEntry.getKey().split("\\.")[0];
					if (!"txt".equals(innerEntry.getValue()) && splitFileName.indexOf(innerSplitFileName) == 0) {
						String title = splitFileName;
						String link = "/picture/sutra/"
								+ PinyinUtil.getPingYin(innerEntry.getKey(), true).replaceAll(" ", "");
						String contentLink = "/document/sutra/"
								+ PinyinUtil.getPingYin(entry.getKey(), true).replaceAll(" ", "");
						String picFileName = outerResources + "picture/sutra/"
								+ PinyinUtil.getPingYin(innerEntry.getKey(), true).replaceAll(" ", "");
						String txtFileName = outerResources + "document/sutra/"
								+ PinyinUtil.getPingYin(entry.getKey(), true).replaceAll(" ", "");
						InputStream is = null;
						OutputStream out = null;
						try {
							is = new FileInputStream(new File(dir, entry.getKey()));
							out = new FileOutputStream(new File(txtFileName));
							IOUtils.write(IOUtils.toString(is, "GBK"), out, "UTF-8");
							FileUtils.copyFile(new File(dir, innerEntry.getKey()), new File(picFileName));
							// FileUtils.copyFile(new File(dir, entry.getKey()),
							// new File(txtFileName));
							Sutra sutra = new Sutra();
							sutra.setContentLink(contentLink);
							sutra.setLink(link);
							sutra.setTitle(title);
							sutraDao.createSutra(sutra);
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							if (is != null) {
								try {
									is.close();
								} catch (IOException e) {
								}
							}
							if (out != null) {
								try {
									out.close();
								} catch (IOException e) {
								}
							}
						}
						isMatchPic = true;
						break;
					}
				}

				if (!isMatchPic) {
					String title = splitFileName;
					String contentLink = "/document/sutra/"
							+ PinyinUtil.getPingYin(entry.getKey(), true).replaceAll(" ", "");
					String txtFileName = outerResources + "document/sutra/"
							+ PinyinUtil.getPingYin(entry.getKey(), true).replaceAll(" ", "");
					InputStream is = null;
					OutputStream out = null;
					try {
						is = new FileInputStream(new File(dir, entry.getKey()));
						out = new FileOutputStream(new File(txtFileName));
						IOUtils.write(IOUtils.toString(is, "GBK"), out, "UTF-8");
						// FileUtils.copyFile(new File(dir, entry.getKey()), new
						// File(txtFileName));
						Sutra sutra = new Sutra();
						sutra.setContentLink(contentLink);
						sutra.setTitle(title);
						sutraDao.createSutra(sutra);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (is != null) {
							try {
								is.close();
							} catch (IOException e) {
							}
						}
						if (out != null) {
							try {
								out.close();
							} catch (IOException e) {
							}
						}
					}
				}
			}
		}
	}

	private void collectLiaotuoData() {
		List<String> categoryList = new ArrayList<String>();
		categoryList.add("wuliangshoujing/");
		categoryList.add("emituojing/");
		categoryList.add("guanwuliangshoujing/");
		categoryList.add("puxianxingyuanpin/");
		categoryList.add("nianfoyuantongzhan/");
		categoryList.add("xinjing/");
		categoryList.add("jingangjing/");
		categoryList.add("yuanjuejing/");
		categoryList.add("lengqiejing/");
		categoryList.add("lengyanjing/");
		categoryList.add("weimojiejing/");
		categoryList.add("liuzutanjing/");
		categoryList.add("huayanjing/");
		categoryList.add("dizangjing/");
		categoryList.add("yaoshijing/");
		categoryList.add("jushelun/");
		categoryList.add("niepanjing/");
		categoryList.add("foyijiaojing/");
		categoryList.add("jieshenmijing/");
		categoryList.add("chengweishilun/");
		categoryList.add("miaofalianhuajing/");
		categoryList.add("shishanyedaojing/");
		categoryList.add("yuqieshidilun/");
		categoryList.add("dachenqixinlun/");
		categoryList.add("fumuennanbaojing/");
		categoryList.add("yulanpenjing/");
		categoryList.add("sishierzhangjing/");
		categoryList.add("badarenjuejing/");
		categoryList.add("zhancashaneyebaojing/");
		categoryList.add("pumenpin/");
		for (String category : categoryList) {
			new CollectLiaotuoDataThread(category, 40000, 70000).start();
			new CollectLiaotuoDataThread(category, 70001, 100000).start();
		}
	}

	private class CollectLiaotuoDataThread extends Thread {

		private String baseUrl = "http://www.liaotuo.org/fojing/";

		private String category;

		private int start;

		private int end;

		public CollectLiaotuoDataThread(String category, int start, int end) {
			super();
			this.category = category;
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			for (int i = start; i <= end; i++) {
				String articleUrl = baseUrl + category + i + ".html";
				try {
					String resultHtml = HttpService.sendGetForHtml(articleUrl);
					String title = HtmlUtil.getElementInnterText(resultHtml, "h1[class].B1_tit");
					if (title != null) {
						long currentTime = System.currentTimeMillis();
						String content = HtmlUtil.getElementInnterText(resultHtml, "div[class].B1_text");
						String imgLink = HtmlUtil.getElementInnerImageLink(resultHtml, "div[class].B1_text");
						String link = null;
						// 保存图片
						if (imgLink != null) {
							URL netUrl = new URL(imgLink);
							netUrl.openConnection();
							InputStream imgIs = netUrl.openStream();
							link = "picture/sutra/" + currentTime + imgLink.substring(imgLink.lastIndexOf("."));
							FileOutputStream fos = new FileOutputStream(outerResources + link);
							IOUtils.copy(imgIs, fos);

							imgIs.close();
							fos.close();
						}
						// 保存经书为txt
						String contentLink = "document/sutra/" + currentTime + ".txt";
						FileUtils.writeStringToFile(new File(outerResources + contentLink), content, "UTF-8");
						// 保存经书记录
						Sutra sutra = new Sutra();
						sutra.setTitle(title);
						if (link != null) {
							sutra.setLink("/" + link);
						}
						sutra.setContentLink("/" + contentLink);
						sutra.setCollectSourceLink(articleUrl);
						sutraDao.createSutra(sutra);
					}
				} catch (HttpClientErrorException e) {
					logger.error("{} " + e.getMessage(), articleUrl);
				} catch (IOException e) {
					logger.error("collectSutraData failed! The url is {}.", articleUrl);
				}
			}
		}

	}

}
