package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Banner;
import com.wangbei.util.enums.BannerTypeEnum;

/**
 * banner图 Repository
 * 
 * @author luomengan
 *
 */
public interface BannerRepository extends Repository<Banner, Integer> {

	public Banner save(Banner banner);

	void delete(Integer id);

	Page<Banner> findAll(Pageable pageable);

	Page<Banner> findByType(BannerTypeEnum type, Pageable pageable);

	List<Banner> findAll();

	List<Banner> findByType(BannerTypeEnum type);

	Banner findById(Integer id);

}
