package com.lucien.spirit.module.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.module.system.model.DictEntry;

@Repository
public interface DictEntryDao extends JpaRepository<DictEntry, Long> {

	@Query("SELECT e FROM DictEntry e WHERE e.dictType.dictTypeId = :dictTypeId")
	public List<DictEntry> findByDictTypeId(@Param("dictTypeId") String dictTypeId);
}
