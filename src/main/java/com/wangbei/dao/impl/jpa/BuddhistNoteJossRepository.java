package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.BuddhistNoteJoss;

/**
 * 佛录佛像 Repository
 * 
 * @author luomengan
 *
 */
public interface BuddhistNoteJossRepository extends Repository<BuddhistNoteJoss, Integer> {

	BuddhistNoteJoss save(BuddhistNoteJoss buddhistNoteJoss);

	void delete(Integer id);

	Page<BuddhistNoteJoss> findAll(Pageable pageable);
	
	List<BuddhistNoteJoss> findAll();

	BuddhistNoteJoss findById(Integer id);

	BuddhistNoteJoss findByBuddhaName(String buddha);
	
}
