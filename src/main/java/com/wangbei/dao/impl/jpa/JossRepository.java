package com.wangbei.dao.impl.jpa;

import com.wangbei.entity.Joss;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

/**
 * @author Created by yuyidi on 2017/7/11.
 * @desc
 */
public interface JossRepository extends Repository<Joss, Integer> {
    public Joss save(Joss joss);

    void delete(Integer id);

    Page<Joss> findAll(Pageable pageable);

    Joss findById(Integer id);
}

