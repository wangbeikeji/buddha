package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.Banner;
import com.wangbei.util.enums.BannerTypeEnum;

/**
 * banner图 Dao
 * 
 * @author luomengan
 *
 */
public interface BannerDao {

	public Banner createBanner(Banner banner);

	public void deleteBannerById(Integer id);

	public Banner updateBanner(Banner banner);

	public Banner retrieveBannerById(Integer id);

	public Page<Banner> pageBanner(int page, int limit);
	
	public Page<Banner> pageBannerByType(BannerTypeEnum type, int page, int limit);
	
	public List<Banner> listBanner();
	
	public List<Banner> listBannerByType(BannerTypeEnum type);

}
