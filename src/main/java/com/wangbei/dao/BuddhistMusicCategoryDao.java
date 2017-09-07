package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.BuddhistMusicCategory;

/**
 * 佛音类别 Dao
 * 
 * @author luomengan
 *
 */
public interface BuddhistMusicCategoryDao {

	public BuddhistMusicCategory createBuddhistMusicCategory(BuddhistMusicCategory buddhistMusicCategory);

	public void deleteBuddhistMusicCategoryById(Integer id);

	public BuddhistMusicCategory updateBuddhistMusicCategory(BuddhistMusicCategory buddhistMusicCategory);

	public BuddhistMusicCategory retrieveBuddhistMusicCategoryById(Integer id);

	public Page<BuddhistMusicCategory> pageBuddhistMusicCategory(int page, int limit);
	
	public List<BuddhistMusicCategory> listBuddhistMusicCategory();

	public List<BuddhistMusicCategory> listBuddhistMusicCategoryByLevel(int level);

	public List<BuddhistMusicCategory> listBuddhistMusicCategoryByParent(BuddhistMusicCategory parent);
	
	public Page<BuddhistMusicCategory> pageBuddhistMusicCategoryByParent(BuddhistMusicCategory parent, int page, int limit);

	public List<BuddhistMusicCategory> listBuddhistMusicRecommendCategory();

	public List<BuddhistMusicCategory> listBuddhistMusicCategoryByNameAndLevel(String name, int level);

	public List<BuddhistMusicCategory> listBuddhistMusicCategoryByParentIdAndIsHomeTop(int parentId, boolean isHomeTop);

	public List<BuddhistMusicCategory> listBuddhistMusicRecommendCategoryByParentId(int parentId);

}
