package com.wangbei.dao;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
public interface BaseDao<T, S extends Serializable> {
    T create(T t);

    void deleteById(S id);

    T update(T t);

    T retrieveById(S id);

    Page<T> page(int page, int limit);

    List<T> list();
}
