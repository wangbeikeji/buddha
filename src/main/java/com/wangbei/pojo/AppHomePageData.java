package com.wangbei.pojo;

import java.util.List;

/**
 * App佛音首页数据
 * 
 * @author luomengan
 *
 */
public class AppHomePageData {

	/**
	 * 顶部推荐列表
	 */
	private List<AppHomeTopData> topRecommendList;

	/**
	 * 推荐专辑列表
	 */
	private List<BuddhistMusicCategoryInfo> recommendAlbumList;

	public List<AppHomeTopData> getTopRecommendList() {
		return topRecommendList;
	}

	public void setTopRecommendList(List<AppHomeTopData> topRecommendList) {
		this.topRecommendList = topRecommendList;
	}

	public List<BuddhistMusicCategoryInfo> getRecommendAlbumList() {
		return recommendAlbumList;
	}

	public void setRecommendAlbumList(List<BuddhistMusicCategoryInfo> recommendAlbumList) {
		this.recommendAlbumList = recommendAlbumList;
	}

}
