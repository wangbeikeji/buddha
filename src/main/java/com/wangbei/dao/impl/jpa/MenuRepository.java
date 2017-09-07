package com.wangbei.dao.impl.jpa;

import org.springframework.data.repository.Repository;

import com.wangbei.entity.Menu;

/**
 * @author yuyidi 2017-06-28 13:56:37
 * @class com.wangbei.dao.impl.jpa.MenuRepository
 * @description 菜单jpa接口
 */
public interface MenuRepository extends Repository<Menu, Integer> {

	Menu findById(Integer id);

	Menu save(Menu menu);

}
