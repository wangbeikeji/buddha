package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Almanac;

/**
 * 黄历+佛历 Repository
 * 
 * @author luomengan
 *
 */
public interface AlmanacRepository extends CurdJpaRepository<Almanac, Integer>, Repository<Almanac, Integer> {

	Almanac findByGregorianCalendar(String gregorianCalendar);

	@Query(value = "select o from Almanac o where o.gregorianCalendar>=?1 and o.gregorianCalendar<=?2 order by gregorianCalendar asc")
	@Modifying
	List<Almanac> findByBetweenYear(String startYear, String endYear);

}
