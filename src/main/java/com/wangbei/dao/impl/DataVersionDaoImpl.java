package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.DataVersionDao;
import com.wangbei.dao.impl.jpa.DataVersionRepository;
import com.wangbei.entity.DataVersion;

/**
 * 数据版本 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class DataVersionDaoImpl implements DataVersionDao {

	@Autowired
	private DataVersionRepository dataVersionRepository;

	@Override
	public DataVersion createDataVersion(DataVersion dataVersion) {
		return dataVersionRepository.save(dataVersion);
	}

	@Override
	public void deleteDataVersionById(Integer id) {
		dataVersionRepository.delete(id);
	}

	@Override
	public DataVersion updateDataVersion(DataVersion dataVersion) {
		return dataVersionRepository.save(dataVersion);
	}

	@Override
	public DataVersion retrieveDataVersionById(Integer id) {
		return dataVersionRepository.findById(id);
	}

	@Override
	public Page<DataVersion> pageDataVersion(int page, int limit) {
		return dataVersionRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<DataVersion> listDataVersion() {
		return dataVersionRepository.findAll();
	}

	@Override
	public List<DataVersion> listCurrentVersion() {
		return dataVersionRepository.findByIsCurrent(true,
				new Sort(new Sort.Order(Direction.DESC, "version"), new Sort.Order(Direction.DESC, "createTime")));
	}

}
