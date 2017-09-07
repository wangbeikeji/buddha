package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.BuddhistMusic;

/**
 * 佛音 Repository
 * 
 * @author luomengan
 *
 */
public interface BuddhistMusicRepository
		extends CurdJpaRepository<BuddhistMusic, Integer>, Repository<BuddhistMusic, Integer> {

	List<BuddhistMusic> findByCategoryId(int categoryId, Sort sort);

	BuddhistMusic findByLink(String link);

	@Query(value = "select t1.* from buddhist_music t1, buddhist_music_category t2 where t1.category_id=t2.id and (t2.id=?1 or t2.parent_id=?1) and t1.is_home_top=?2 order by t1.home_top_sort_num desc, t1.sort_num desc", nativeQuery = true)
	List<BuddhistMusic> findByCategoryIdAndIsHomeTop(int categoryId, boolean isHomeTop);

	Page<BuddhistMusic> findByCategoryId(int categoryId, Pageable pagable);

}
