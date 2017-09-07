package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.BuddhistMusicCategoryDao;
import com.wangbei.dao.impl.jpa.BuddhistMusicCategoryRepository;
import com.wangbei.entity.BuddhistMusicCategory;

/**
 * 佛音类别 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class BuddhistMusicCategoryDaoImpl implements BuddhistMusicCategoryDao {

	@Autowired
	private BuddhistMusicCategoryRepository buddhistMusicCategoryRepository;

	@Override
	public BuddhistMusicCategory createBuddhistMusicCategory(BuddhistMusicCategory buddhistMusicCategory) {
		return buddhistMusicCategoryRepository.save(buddhistMusicCategory);
	}

	@Override
	public void deleteBuddhistMusicCategoryById(Integer id) {
		buddhistMusicCategoryRepository.delete(id);
	}

	@Override
	public BuddhistMusicCategory updateBuddhistMusicCategory(BuddhistMusicCategory buddhistMusicCategory) {
		return buddhistMusicCategoryRepository.save(buddhistMusicCategory);
	}

	@Override
	public BuddhistMusicCategory retrieveBuddhistMusicCategoryById(Integer id) {
		return buddhistMusicCategoryRepository.findById(id);
	}

	@Override
	public Page<BuddhistMusicCategory> pageBuddhistMusicCategory(int page, int limit) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "sortNum"));
		return buddhistMusicCategoryRepository.findAll(new PageRequest(page, limit, sort));
	}

	@Override
	public List<BuddhistMusicCategory> listBuddhistMusicCategory() {
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "parent.id"), new Sort.Order(Direction.DESC, "sortNum"));
		return buddhistMusicCategoryRepository.findAll(sort);
	}

	@Override
	public List<BuddhistMusicCategory> listBuddhistMusicCategoryByLevel(int level) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "sortNum"));
		return buddhistMusicCategoryRepository.findByLevel(level, sort);
	}

	@Override
	public List<BuddhistMusicCategory> listBuddhistMusicCategoryByParent(BuddhistMusicCategory parent) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "sortNum"));
		return buddhistMusicCategoryRepository.findByParent(parent, sort);
	}

	@Override
	public List<BuddhistMusicCategory> listBuddhistMusicRecommendCategory() {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "sortNum"));
		return buddhistMusicCategoryRepository.findByIsRecommend(true, sort);
	}

	@Override
	public List<BuddhistMusicCategory> listBuddhistMusicCategoryByNameAndLevel(String name, int level) {
		return buddhistMusicCategoryRepository.findByNameAndLevel(name, level);
	}

	@Override
	public List<BuddhistMusicCategory> listBuddhistMusicCategoryByParentIdAndIsHomeTop(int parentId,
			boolean isHomeTop) {
		return buddhistMusicCategoryRepository.findByParentIdAndIsHomeTop(parentId, true);
	}

	@Override
	public List<BuddhistMusicCategory> listBuddhistMusicRecommendCategoryByParentId(int parentId) {
		return buddhistMusicCategoryRepository.findByIsRecommendAndParentId(true, parentId);
	}

	@Override
	public Page<BuddhistMusicCategory> pageBuddhistMusicCategoryByParent(BuddhistMusicCategory parent, int page,
			int limit) {
		return buddhistMusicCategoryRepository.findByParent(parent, new PageRequest(page, limit));
	}

}
