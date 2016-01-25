package com.lucien.spirit.module.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lucien.spirit.module.security.model.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

	@Query("SELECT r FROM Resource r WHERE r.parent is null)")
	public List<Resource> findTopList();
}
