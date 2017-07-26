package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.BannerDao;
import com.wangbei.dao.impl.jpa.BannerRepository;
import com.wangbei.entity.Banner;
import com.wangbei.util.enums.BannerTypeEnum;

/**
 * banner图 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class BannerDaoImpl implements BannerDao {

	@Autowired
	private BannerRepository bannerRepository;

	@Override
	public Banner createBanner(Banner banner) {
		return bannerRepository.save(banner);
	}

	@Override
	public void deleteBannerById(Integer id) {
		bannerRepository.delete(id);
	}

	@Override
	public Banner updateBanner(Banner banner) {
		return bannerRepository.save(banner);
	}

	@Override
	public Banner retrieveBannerById(Integer id) {
		return bannerRepository.findById(id);
	}

	@Override
	public Page<Banner> pageBanner(int page, int limit) {
		Pageable pageable = new PageRequest(page, limit, new Sort(new Sort.Order(Direction.DESC, "sortNum")));
		return bannerRepository.findAll(pageable);
	}

	@Override
	public Page<Banner> pageBannerByType(BannerTypeEnum type, int page, int limit) {
		Pageable pageable = new PageRequest(page, limit, new Sort(new Sort.Order(Direction.DESC, "sortNum")));
		return bannerRepository.findByType(type, pageable);
	}

	@Override
	public List<Banner> listBanner() {
		return bannerRepository.findAll();
	}

	@Override
	public List<Banner> listBannerByType(BannerTypeEnum type) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "sortNum"));
		return bannerRepository.findByType(type, sort);
	}

}
