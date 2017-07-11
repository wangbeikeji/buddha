package com.wangbei.dao;

import com.wangbei.entity.Joss;
import org.springframework.data.domain.Page;

/**
 * @author Created by yuyidi on 2017/7/11.
 * @desc
 */
public interface JossDao {
    public Joss createJoss(Joss joss);

    public void deleteJossById(Integer id);

    public Joss updateJoss(Joss joss);

    public Joss retrieveJossById(Integer id);

    public Page<Joss> pageJoss(int offset, int limit);
}