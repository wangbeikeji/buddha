package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.BannerDao;
import com.wangbei.entity.Banner;
import com.wangbei.util.enums.BannerTypeEnum;

/**
 * banner图 Service
 * 
 * @author luomengan
 *
 */
@Service
public class BannerService {

	@Autowired
	private BannerDao bannerDao;

	public Banner getBannerInfo(Integer id) {
		return bannerDao.retrieveBannerById(id);
	}

	@Transactional
	public Banner addBanner(Banner banner) {
		return bannerDao.createBanner(banner);
	}

	@Transactional
	public Banner modifyBanner(Banner banner) {
		return bannerDao.updateBanner(banner);
	}

	@Transactional
	public void deleteBanner(Integer id) {
		bannerDao.deleteBannerById(id);
	}

	public Page<Banner> banners(int page, int limit) {
		return bannerDao.pageBanner(page, limit);
	}

	public Page<Banner> bannersByType(BannerTypeEnum type, int page, int limit) {
		return bannerDao.pageBannerByType(type, page, limit);
	}

	public List<Banner> list() {
		return bannerDao.listBanner();
	}

	public List<Banner> listByType(BannerTypeEnum type) {
		return bannerDao.listBannerByType(type);
	}

}
