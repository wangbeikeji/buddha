package com.wangbei.service;

import java.io.File;
import java.io.FileOutputStream;
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
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.BuddhistMusicCategoryDao;
import com.wangbei.dao.BuddhistMusicDao;
import com.wangbei.entity.BuddhistMusic;
import com.wangbei.entity.BuddhistMusicCategory;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.AppHomePageData;
import com.wangbei.pojo.AppHomeTopData;
import com.wangbei.util.PinyinUtil;

/**
 * 佛音 Service
 * 
 * @author luomengan
 *
 */
@Service
public class BuddhistMusicService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BuddhistMusicDao buddhistMusicDao;

	@Autowired
	private BuddhistMusicCategoryDao categoryDao;

	@Autowired
	private BuddhistMusicCategoryService categoryService;

	@Value("${custom.outer.resources}")
	private String outerResources;

	public BuddhistMusic getBuddhistMusicInfo(Integer id) {
		BuddhistMusic music = buddhistMusicDao.retrieveBuddhistMusicById(id);
		if (music != null) {
			BuddhistMusicCategory category = categoryDao.retrieveBuddhistMusicCategoryById(music.getCategoryId());
			if (category != null) {
				music.setCoverLink1(category.getCoverLink4());
				music.setCoverLink2(category.getCoverLink5());
			}
		}
		return music;
	}

	@Transactional
	public BuddhistMusic addBuddhistMusic(BuddhistMusic buddhistMusic) {
		String link = buddhistMusic.getLink();
		if (link != null && link.indexOf("href=") >= 0) {
			// 表示当前为富文件编辑器添加的，对link进行处理
			int hrefIndex = link.indexOf("href=");
			int titleIndex = link.indexOf("title=");

			if (hrefIndex > 0 && titleIndex > 0) {
				link = link.substring(hrefIndex + 6, titleIndex - 2);
				buddhistMusic.setLink(link);
				// contentLink =
				// contentLink.substring(contentLink.indexOf("buddha") + 6);
				// sutra.setContentLink("http:" + contentLink);
			}
		}
		return buddhistMusicDao.createBuddhistMusic(buddhistMusic);
	}

	@Transactional
	public BuddhistMusic modifyBuddhistMusic(BuddhistMusic buddhistMusic) {
		String link = buddhistMusic.getLink();
		if (link != null && link.indexOf("href=") >= 0) {
			// 表示当前为富文件编辑器添加的，对link进行处理
			int hrefIndex = link.indexOf("href=");
			int titleIndex = link.indexOf("title=");

			if (hrefIndex > 0 && titleIndex > 0) {
				link = link.substring(hrefIndex + 6, titleIndex - 2);
				buddhistMusic.setLink(link);
				// contentLink =
				// contentLink.substring(contentLink.indexOf("buddha") + 6);
				// sutra.setContentLink("http:" + contentLink);
			}
		}
		return buddhistMusicDao.updateBuddhistMusic(buddhistMusic);
	}

	@Transactional
	public void deleteBuddhistMusic(Integer id) {
		buddhistMusicDao.deleteBuddhistMusicById(id);
	}

	public Page<BuddhistMusic> buddhistMusics(int page, int limit) {
		return buddhistMusicDao.pageBuddhistMusic(page, limit);
	}

	public List<BuddhistMusic> list() {
		return buddhistMusicDao.listBuddhistMusic();
	}

	public List<BuddhistMusic> listByCategory(int categoryId) {
		List<BuddhistMusic> result = new ArrayList<>();
		BuddhistMusicCategory category = categoryDao.retrieveBuddhistMusicCategoryById(categoryId);
		if (category == null) {
			throw new ServiceException(ExceptionEnum.BUDDHISTMUSICCATEGORY_NOTEXIST_EXCEPTION);
		}
		innerListByCategory(result, category);
		return result;
	}

	public void innerListByCategory(List<BuddhistMusic> list, BuddhistMusicCategory category) {
		List<BuddhistMusic> musicList = buddhistMusicDao.listBuddhistMusicByCategoryId(category.getId());
		if (musicList != null && musicList.size() > 0) {
			list.addAll(musicList);
		}
		List<BuddhistMusicCategory> childCategoryList = categoryDao.listBuddhistMusicCategoryByParent(category);
		if (childCategoryList != null && childCategoryList.size() > 0) {
			for (BuddhistMusicCategory childCategory : childCategoryList) {
				innerListByCategory(list, childCategory);
			}
		}
	}

	/**
	 * 根据音乐文件夹生成专辑和音乐记录，后台使用，不涉及到业务逻辑
	 */
	public void autoCreateByDir() throws Exception {
		String musicDirStr = outerResources + "attachment/music";
		String httpBase = "http://10.0.0.4:8080/buddha";
		File musicDir = new File(musicDirStr);
		if (musicDir.exists()) {
			File[] firstLevelFiles = musicDir.listFiles();
			if (firstLevelFiles != null && firstLevelFiles.length > 0) {
				for (int i = 0; i < firstLevelFiles.length; i++) {
					File firstLevelFile = firstLevelFiles[i];
					if (firstLevelFile.isDirectory()) {
						// 处理一级目录
						String name = firstLevelFile.getName();
						String newName = PinyinUtil.getPingYin(name);
						if (!name.equals(newName)) {
							File newFirstLevelFile = new File(firstLevelFile.getParentFile(), newName);
							if (newFirstLevelFile.exists()) {
								FileUtils.moveDirectoryToDirectory(firstLevelFile, newFirstLevelFile, false);
							} else {
								firstLevelFile.renameTo(newFirstLevelFile);
							}
							firstLevelFile = newFirstLevelFile;
						}

						List<BuddhistMusicCategory> firstLevelCategoryList = categoryDao
								.listBuddhistMusicCategoryByNameAndLevel(name, 1);
						BuddhistMusicCategory firstLevelCategory = null;
						if (firstLevelCategoryList != null && firstLevelCategoryList.size() > 0) {
							firstLevelCategory = firstLevelCategoryList.get(0);
						} else {
							firstLevelCategoryList = categoryDao.listBuddhistMusicCategoryByNameAndLevel(newName, 1);
							if (firstLevelCategoryList != null && firstLevelCategoryList.size() > 0) {
								firstLevelCategory = firstLevelCategoryList.get(0);
							} else {
								firstLevelCategory = new BuddhistMusicCategory();
								firstLevelCategory.setTxtDescription(name);
								firstLevelCategory.setLevel(1);
								firstLevelCategory.setName(name);
								List<BuddhistMusicCategory> checkLevelSize = categoryDao
										.listBuddhistMusicCategoryByLevel(1);
								if (checkLevelSize != null && checkLevelSize.size() > 0) {
									firstLevelCategory.setSortNum(checkLevelSize.size() + 1);
								} else {
									firstLevelCategory.setSortNum(1);
								}
								categoryDao.createBuddhistMusicCategory(firstLevelCategory);
							}
						}
						// 处理一级目录下的文件和目录
						File[] firstLevelChildFileList = firstLevelFile.listFiles();
						if (firstLevelChildFileList != null && firstLevelChildFileList.length > 0) {
							for (File firstLevelChildFile : firstLevelChildFileList) {
								String name1 = firstLevelChildFile.getName();
								String name2 = PinyinUtil.getPingYin(name1);
								if (!name1.equals(name2)) {
									File newFirstLevelChildFile = new File(firstLevelChildFile.getParentFile(), name2);
									if (newFirstLevelChildFile.exists()) {
										if (newFirstLevelChildFile.isDirectory()) {
											FileUtils.moveDirectoryToDirectory(firstLevelChildFile,
													newFirstLevelChildFile, false);
										} else {
											FileUtils.forceDelete(firstLevelChildFile);
										}
									} else {
										firstLevelChildFile.renameTo(newFirstLevelChildFile);
									}
									firstLevelChildFile = newFirstLevelChildFile;
								}
								if (firstLevelChildFile.isDirectory()) {
									// 处理一级目录下的目录
									List<BuddhistMusicCategory> secondLevelCategoryList = categoryDao
											.listBuddhistMusicCategoryByNameAndLevel(name1, 2);
									BuddhistMusicCategory secondLevelCategory = null;
									if (secondLevelCategoryList != null && secondLevelCategoryList.size() > 0) {
										secondLevelCategory = secondLevelCategoryList.get(0);
									} else {
										secondLevelCategoryList = categoryDao
												.listBuddhistMusicCategoryByNameAndLevel(name2, 2);
										if (secondLevelCategoryList != null && secondLevelCategoryList.size() > 0) {
											secondLevelCategory = secondLevelCategoryList.get(0);
										} else {
											secondLevelCategory = new BuddhistMusicCategory();
											secondLevelCategory.setTxtDescription(name1);
											secondLevelCategory.setIsRecommend(false);
											secondLevelCategory.setLevel(2);
											secondLevelCategory.setName(name1);
											secondLevelCategory.setParent(firstLevelCategory);
											List<BuddhistMusicCategory> innerCheck = categoryDao
													.listBuddhistMusicCategoryByParent(firstLevelCategory);
											if (innerCheck != null && innerCheck.size() > 0) {
												secondLevelCategory.setSortNum(innerCheck.size() + 1);
											} else {
												secondLevelCategory.setSortNum(1);
											}
											categoryDao.createBuddhistMusicCategory(secondLevelCategory);
										}
									}
									// 处理二级目录下的文件
									File[] secondLevelFiles = firstLevelChildFile.listFiles();
									if (secondLevelFiles != null && secondLevelFiles.length > 0) {
										for (File secondLevelFile : secondLevelFiles) {
											if (secondLevelFile.isFile()) {
												String name3 = secondLevelFile.getName();
												String name4 = PinyinUtil.getPingYin(name3);
												if (!name3.equals(name4)) {
													File newSecondLevelFile = new File(secondLevelFile.getParentFile(),
															name4);
													if (newSecondLevelFile.exists()) {
														FileUtils.forceDelete(secondLevelFile);
													}
													secondLevelFile = newSecondLevelFile;
												}
												String link = httpBase + "/attachment/music/" + newName + "/" + name2
														+ "/" + name4;
												BuddhistMusic music = buddhistMusicDao
														.retrieveBuddhistMusicByLink(link);
												if (music == null) {
													music = new BuddhistMusic();
													music.setAuthor("无");
													music.setCategoryId(secondLevelCategory.getId());
													music.setIsRecommend(false);
													music.setLink(link);
													music.setDuration(getMusicDuration(link));
													if (name3.indexOf("-") > 0) {
														int gangIndex = name3.indexOf("-");
														music.setAuthor(name3.substring(0, gangIndex).trim());
														music.setName(
																name3.substring(gangIndex + 1, name3.lastIndexOf("."))
																		.trim());
													} else {
														music.setName(name3.substring(0, name3.lastIndexOf(".")));
													}

													List<BuddhistMusic> innerCheck = buddhistMusicDao
															.listBuddhistMusicByCategoryId(secondLevelCategory.getId());
													if (innerCheck != null && innerCheck.size() > 0) {
														music.setSortNum(innerCheck.size() + 1);
													} else {
														music.setSortNum(1);
													}
													buddhistMusicDao.createBuddhistMusic(music);
												} else {
													System.out.println("未保存：" + name3);
												}
											}
										}
									}
								} else {
									// 处理一级目录下的文件
									String link = httpBase + "/attachment/music/" + newName + "/" + name2;
									BuddhistMusic music = buddhistMusicDao.retrieveBuddhistMusicByLink(link);
									if (music == null) {
										music = new BuddhistMusic();
										music.setAuthor("无");
										music.setCategoryId(firstLevelCategory.getId());
										music.setIsRecommend(false);
										music.setLink(link);
										music.setDuration(getMusicDuration(link));
										if (name1.indexOf("-") > 0) {
											int gangIndex = name1.indexOf("-");
											music.setAuthor(name1.substring(0, gangIndex).trim());
											music.setName(
													name1.substring(gangIndex + 1, name1.lastIndexOf(".")).trim());
										} else {
											music.setName(name1.substring(0, name1.lastIndexOf(".")));
										}
										List<BuddhistMusic> innerCheck = buddhistMusicDao
												.listBuddhistMusicByCategoryId(firstLevelCategory.getId());
										if (innerCheck != null && innerCheck.size() > 0) {
											music.setSortNum(innerCheck.size() + 1);
										} else {
											music.setSortNum(1);
										}
										buddhistMusicDao.createBuddhistMusic(music);
									} else {
										System.out.println("未保存：" + name1);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public AppHomePageData getAppHomePageData(int categoryId) {
		AppHomePageData result = new AppHomePageData();
		result.setRecommendAlbumList(categoryService.listRecommendCategoryByParentId(categoryId));

		// 顶部推荐列表
		List<AppHomeTopData> topRecommendList = new ArrayList<>();
		List<BuddhistMusic> musicList = buddhistMusicDao.listBuddhistMusicByCategoryIdAndIsHomeTop(categoryId, true);
		List<BuddhistMusicCategory> categoryList = categoryDao
				.listBuddhistMusicCategoryByParentIdAndIsHomeTop(categoryId, true);
		if (musicList != null && musicList.size() > 0) {
			for (BuddhistMusic music : musicList) {
				BuddhistMusicCategory category = categoryDao.retrieveBuddhistMusicCategoryById(music.getCategoryId());
				if (category != null) {
					music.setCoverLink1(category.getCoverLink4());
					music.setCoverLink2(category.getCoverLink5());
				}
				topRecommendList.add(new AppHomeTopData(music, category.getCoverLink4()));
			}
		}
		if (categoryList != null && categoryList.size() > 0) {
			for (BuddhistMusicCategory category : categoryList) {
				topRecommendList.add(new AppHomeTopData(category));
			}
		}
		result.setTopRecommendList(topRecommendList);

		return result;
	}

	public Page<BuddhistMusic> pageByCategoryId(int categoryId, int page, int limit) {
		Page<BuddhistMusic> result = buddhistMusicDao.pageByCategoryId(categoryId, page, limit);
		if (result != null && result.getContent() != null && result.getContent().size() > 0) {
			BuddhistMusicCategory category = categoryDao.retrieveBuddhistMusicCategoryById(categoryId);
			if (category != null) {
				for (BuddhistMusic music : result.getContent()) {
					music.setCoverLink1(category.getCoverLink4());
					music.setCoverLink2(category.getCoverLink5());
				}
			}
		}
		return result;
	}

	@Transactional
	public synchronized void paly(int id) {
		BuddhistMusic music = buddhistMusicDao.retrieveBuddhistMusicById(id);
		if (music != null) {
			int musicOldTimes = music.getPlayTimes() != null ? music.getPlayTimes() : 0;
			music.setPlayTimes(musicOldTimes + 1);
			buddhistMusicDao.updateBuddhistMusic(music);

			BuddhistMusicCategory category = categoryDao.retrieveBuddhistMusicCategoryById(music.getCategoryId());
			if (category != null) {
				int categoryOldTimes = music.getPlayTimes() != null ? music.getPlayTimes() : 0;
				category.setPlayTimes(categoryOldTimes + 1);
				categoryDao.updateBuddhistMusicCategory(category);
			}
		}
	}

	public void buildMusicDuration(boolean isBuildAll) {
		List<BuddhistMusic> list = buddhistMusicDao.listBuddhistMusic();
		if (list != null && list.size() > 0) {
			for (BuddhistMusic music : list) {
				String link = music.getLink();
				if (link != null && link.toLowerCase().endsWith(".mp3")) {
					if (!isBuildAll && music.getDuration() != null && music.getDuration() > 0) {
						continue;
					}
					Integer duration = getMusicDuration(link);
					if (duration > 0) {
						music.setDuration(duration);
						buddhistMusicDao.updateBuddhistMusic(music);
					}
				}
			}
		}
	}

	/**
	 * 获取音乐时长
	 */
	private Integer getMusicDuration(String link) {
		InputStream is = null;
		OutputStream out = null;
		File tempFile = null;
		try {
			URL url = new URL(link);
			url.openConnection();
			is = url.openStream();
			tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".mp3");
			out = new FileOutputStream(tempFile);
			IOUtils.copy(is, out);
			MP3File mp3File = (MP3File) AudioFileIO.read(tempFile);
			MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
			// 单位为秒
			return audioHeader.getTrackLength();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取音乐播放时长失败！" + link);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
			}
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
			}
			if (tempFile != null) {
				try {
					FileUtils.forceDelete(tempFile);
				} catch (IOException e) {
				}
			}
		}
		return 0;
	}

	public List<BuddhistMusic> adminList() {
		List<BuddhistMusic> list = buddhistMusicDao.listBuddhistMusic();
		if (list != null && list.size() > 0) {
			List<BuddhistMusicCategory> categoryList = categoryDao.listBuddhistMusicCategory();
			Map<Integer, BuddhistMusicCategory> idNameList = new HashMap<>();
			for (BuddhistMusicCategory category : categoryList) {
				idNameList.put(new Integer(category.getId()), category);
			}
			for (int i = list.size() - 1; i >= 0; i--) {
				BuddhistMusic music = list.get(i);
				if (idNameList.containsKey(music.getCategoryId())) {
					if (idNameList.get(music.getCategoryId()).getLevel() == 2) {
						music.setCategoryName(idNameList.get(music.getCategoryId()).getName());
					} else {
						list.remove(music);
					}
				} else {
					list.remove(music);
				}
			}
		}
		return list;
	}

}
