package com.wangbei.dao;

import com.wangbei.entity.Menu;

/**
 * @author Created by yuyidi on 2017/6/27.
 * @desc
 */
public interface MenuDao {
    Menu retrieveMenuById(Integer id);

    Menu createMenu(Menu menu);

    Menu updateMenu(Menu menu);
}
