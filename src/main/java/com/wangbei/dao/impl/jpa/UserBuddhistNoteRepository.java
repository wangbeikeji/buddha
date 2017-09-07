package com.wangbei.dao.impl.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserBuddhistNote;
import com.wangbei.util.enums.BuddhistNoteTypeEnum;

/**
 * 用户佛录 Repository
 * 
 * @author luomengan
 *
 */
public interface UserBuddhistNoteRepository
		extends CurdJpaRepository<UserBuddhistNote, Integer>, Repository<UserBuddhistNote, Integer> {

	Page<UserBuddhistNote> findByUserIdAndType(Integer userId, BuddhistNoteTypeEnum type, Pageable pageable);

	Page<UserBuddhistNote> findByTypeAndIsPublic(BuddhistNoteTypeEnum type, boolean isPublic, Pageable pageable);

}
