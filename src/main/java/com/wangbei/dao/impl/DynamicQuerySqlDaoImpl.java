package com.wangbei.dao.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.DynamicQuerySqlDao;
import com.wangbei.pojo.MethodDesc;

/**
 * 动态执行查询sql
 * 
 * @author luomengan
 *
 */
@Repository
public class DynamicQuerySqlDaoImpl implements DynamicQuerySqlDao {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public <T> List<T> execute(Class<T> clazz, String sql, Map<Integer, MethodDesc> setMethodMap) {
		try {
			List<T> result = new ArrayList<>();
			Query query = entityManager.createNativeQuery(sql);
			List<Object[]> queryList = query.getResultList();
			if (queryList != null && queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					T inner = clazz.newInstance();
					for (int j = 0; j < queryList.get(i).length; j++) {
						if (setMethodMap.containsKey(new Integer(j))) {
							String mName = setMethodMap.get(new Integer(j)).getName();
							Class<?>[] paramTypes = setMethodMap.get(new Integer(j)).getParamTypes();
							Method m = clazz.getMethod(mName, paramTypes);
							Object obj = queryList.get(i)[j];
							m.invoke(inner, obj);
						}
					}
					result.add(inner);
				}
			}
			return result;
		} catch (Exception ex) {
			throw new RuntimeException("DynamicQuerySql execute failed!", ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T executeComputeSql(String sql) {
		try {
			Query query = entityManager.createNativeQuery(sql);
			return (T) query.getSingleResult();
		} catch (Exception ex) {
			throw new RuntimeException("DynamicQuerySql execute failed!", ex);
		}
	}

}
