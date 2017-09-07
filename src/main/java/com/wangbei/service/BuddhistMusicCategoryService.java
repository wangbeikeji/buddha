package com.wangbei.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.BuddhistMusicCategoryDao;
import com.wangbei.entity.BuddhistMusicCategory;
import com.wangbei.pojo.BuddhistMusicCategoryInfo;

/**
 * 佛音类别 Service
 * 
 * @author luomengan
 *
 */
@Service
public class BuddhistMusicCategoryService {

	@Autowired
	private BuddhistMusicCategoryDao buddhistMusicCategoryDao;

	public BuddhistMusicCategoryInfo getBuddhistMusicCategoryInfo(Integer id) {
		BuddhistMusicCategory entity = buddhistMusicCategoryDao.retrieveBuddhistMusicCategoryById(id);
		if (entity != null) {
			return new BuddhistMusicCategoryInfo(entity);
		}
		return null;
	}

	@Transactional
	public BuddhistMusicCategoryInfo addBuddhistMusicCategory(BuddhistMusicCategoryInfo buddhistMusicCategoryInfo) {
		BuddhistMusicCategory entity = buddhistMusicCategoryInfo.toBuddhistMusicCategoryEntity();
		if (buddhistMusicCategoryInfo.getLevel() == 2 && buddhistMusicCategoryInfo.getParentId() != null && buddhistMusicCategoryInfo.getParentId() > 0) {
			BuddhistMusicCategory parent = buddhistMusicCategoryDao
					.retrieveBuddhistMusicCategoryById(buddhistMusicCategoryInfo.getParentId());
			if (parent != null) {
				entity.setParent(parent);
				entity.setLevel(parent.getLevel() + 1);
			} else {
				entity.setLevel(1);
			}
		}
		buddhistMusicCategoryDao.createBuddhistMusicCategory(entity);
		if (entity != null) {
			return new BuddhistMusicCategoryInfo(entity);
		}
		return null;
	}

	@Transactional
	public BuddhistMusicCategoryInfo modifyBuddhistMusicCategory(BuddhistMusicCategoryInfo buddhistMusicCategoryInfo) {
		BuddhistMusicCategory entity = buddhistMusicCategoryInfo.toBuddhistMusicCategoryEntity();
		if (buddhistMusicCategoryInfo.getLevel() == 2 && buddhistMusicCategoryInfo.getParentId() != null && buddhistMusicCategoryInfo.getParentId() > 0) {
			BuddhistMusicCategory parent = buddhistMusicCategoryDao
					.retrieveBuddhistMusicCategoryById(buddhistMusicCategoryInfo.getParentId());
			if (parent != null) {
				entity.setParent(parent);
				entity.setLevel(parent.getLevel() + 1);
			} else {
				entity.setLevel(1);
			}
		}
		if (entity != null) {
			buddhistMusicCategoryDao.updateBuddhistMusicCategory(entity);
			return new BuddhistMusicCategoryInfo(entity);
		}
		return null;
	}

	@Transactional
	public void deleteBuddhistMusicCategory(Integer id) {
		buddhistMusicCategoryDao.deleteBuddhistMusicCategoryById(id);
	}

	public Page<BuddhistMusicCategoryInfo> buddhistMusicCategorys(int page, int limit) {
		Page<BuddhistMusicCategory> pageData = buddhistMusicCategoryDao.pageBuddhistMusicCategory(page, limit);
		List<BuddhistMusicCategoryInfo> infoList = new ArrayList<>();
		List<BuddhistMusicCategory> entityList = pageData.getContent();
		if (entityList != null && entityList.size() > 0) {
			for (BuddhistMusicCategory entity : entityList) {
				infoList.add(new BuddhistMusicCategoryInfo(entity));
			}
		}
		return new PageImpl<>(infoList, new PageRequest(page, limit), pageData.getTotalElements());
	}

	public List<BuddhistMusicCategoryInfo> list() {
		List<BuddhistMusicCategoryInfo> infoList = new ArrayList<>();
		List<BuddhistMusicCategory> entityList = buddhistMusicCategoryDao.listBuddhistMusicCategory();
		if (entityList != null && entityList.size() > 0) {
			for (BuddhistMusicCategory entity : entityList) {
				infoList.add(new BuddhistMusicCategoryInfo(entity));
			}
		}
		return infoList;
	}

	public List<BuddhistMusicCategoryInfo> listTopCategory() {
		List<BuddhistMusicCategoryInfo> infoList = new ArrayList<>();
		List<BuddhistMusicCategory> entityList = buddhistMusicCategoryDao.listBuddhistMusicCategoryByLevel(1);
		if (entityList != null && entityList.size() > 0) {
			for (BuddhistMusicCategory entity : entityList) {
				infoList.add(new BuddhistMusicCategoryInfo(entity));
			}
		}
		return infoList;
	}

	public Page<BuddhistMusicCategoryInfo> listAlbumCategoryByParent(int parentId, int page, int limit) {
		BuddhistMusicCategory parent = buddhistMusicCategoryDao.retrieveBuddhistMusicCategoryById(parentId);
		if (parent != null) {
			Page<BuddhistMusicCategory> pageData = buddhistMusicCategoryDao.pageBuddhistMusicCategoryByParent(parent,
					page, limit);
			List<BuddhistMusicCategoryInfo> infoList = new ArrayList<>();
			if (pageData.getContent() != null && pageData.getContent().size() > 0) {
				for (BuddhistMusicCategory entity : pageData.getContent()) {
					infoList.add(new BuddhistMusicCategoryInfo(entity));
				}
			}
			return new PageImpl<>(infoList, new PageRequest(page, limit), pageData.getTotalElements());
		} else {
			return new PageImpl<>(null, new PageRequest(page, limit), 0);
		}
	}

	public List<BuddhistMusicCategoryInfo> listRecommendCategory() {
		List<BuddhistMusicCategoryInfo> infoList = new ArrayList<>();
		List<BuddhistMusicCategory> entityList = buddhistMusicCategoryDao.listBuddhistMusicRecommendCategory();
		if (entityList != null && entityList.size() > 0) {
			for (BuddhistMusicCategory entity : entityList) {
				infoList.add(new BuddhistMusicCategoryInfo(entity));
			}
		}
		return infoList;
	}

	public List<BuddhistMusicCategoryInfo> listRecommendCategoryByParentId(int parentId) {
		List<BuddhistMusicCategoryInfo> infoList = new ArrayList<>();
		List<BuddhistMusicCategory> entityList = buddhistMusicCategoryDao
				.listBuddhistMusicRecommendCategoryByParentId(parentId);
		if (entityList != null && entityList.size() > 0) {
			for (BuddhistMusicCategory entity : entityList) {
				infoList.add(new BuddhistMusicCategoryInfo(entity));
			}
		}
		return infoList;
	}

}
