package com.wangbei.dao;

import java.util.List;
import java.util.Map;

import com.wangbei.pojo.MethodDesc;

/**
 * 动态执行查询sql
 * 
 * @author luomengan
 *
 */
public interface DynamicQuerySqlDao {

	public <T> List<T> execute(Class<T> clazz, String sql, Map<Integer, MethodDesc> setMethodMap);

	public <T> T executeComputeSql(String sql);

}
