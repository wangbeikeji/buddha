package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.BuddhistMusicCategory;

/**
 * 佛音类别 Repository
 * 
 * @author luomengan
 *
 */
public interface BuddhistMusicCategoryRepository
		extends CurdJpaRepository<BuddhistMusicCategory, Integer>, Repository<BuddhistMusicCategory, Integer> {

	List<BuddhistMusicCategory> findByLevel(int level, Sort sort);

	List<BuddhistMusicCategory> findByParent(BuddhistMusicCategory parent, Sort sort);

	List<BuddhistMusicCategory> findAll(Sort sort);

	List<BuddhistMusicCategory> findByIsRecommend(boolean isRecommend, Sort sort);

	List<BuddhistMusicCategory> findByNameAndLevel(String name, int level);

	List<BuddhistMusicCategory> findByIsHomeTop(boolean b, Sort sort);

	@Query(value = "select * from buddhist_music_category where parent_id=?1 and is_home_top=?2 order by home_top_sort_num desc, sort_num desc", nativeQuery = true)
	List<BuddhistMusicCategory> findByParentIdAndIsHomeTop(int parentId, boolean isHomeTop);

	@Query(value = "select * from buddhist_music_category where is_recommend=?1 and parent_id=?2 order by sort_num desc", nativeQuery = true)
	List<BuddhistMusicCategory> findByIsRecommendAndParentId(boolean isRecommend, int parentId);

	Page<BuddhistMusicCategory> findByParent(BuddhistMusicCategory parent, Pageable page);

}
