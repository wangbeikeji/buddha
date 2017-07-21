package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.AppVersionDao;
import com.wangbei.dao.impl.jpa.DataVersionRepository;
import com.wangbei.entity.AppVersion;

/**
 * 数据版本 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class AppVersionDaoImpl implements AppVersionDao {

	@Autowired
	private DataVersionRepository dataVersionRepository;

	@Override
	public AppVersion createDataVersion(AppVersion appVersion) {
		return dataVersionRepository.save(appVersion);
	}

	@Override
	public void deleteDataVersionById(Integer id) {
		dataVersionRepository.delete(id);
	}

	@Override
	public AppVersion updateDataVersion(AppVersion appVersion) {
		return dataVersionRepository.save(appVersion);
	}

	@Override
	public AppVersion retrieveDataVersionById(Integer id) {
		return dataVersionRepository.findById(id);
	}

	@Override
	public Page<AppVersion> pageDataVersion(int page, int limit) {
		return dataVersionRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<AppVersion> listDataVersion() {
		return dataVersionRepository.findAll();
	}

	@Override
	public List<AppVersion> listCurrentVersion() {
		return dataVersionRepository.findByIsCurrent(true,
				new Sort(new Sort.Order(Direction.DESC, "version"), new Sort.Order(Direction.DESC, "createTime")));
	}

}
