package com.wangbei.dao.impl.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
public interface CurdJpaRepository<T, ID extends Serializable> {

	T save(T t);

	void delete(ID id);

	Page<T> findAll(Pageable pageable);

	List<T> findAll();

	T findById(ID id);

}
