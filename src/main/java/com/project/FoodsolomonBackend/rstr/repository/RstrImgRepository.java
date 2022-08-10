package com.project.FoodsolomonBackend.rstr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.FoodsolomonBackend.rstr.entity.RstrImg;

public interface RstrImgRepository extends JpaRepository<RstrImg, Long>{

	@Query(value="select r from RstrImg r where r.rstrId = :rstrId and r.status='Y'")
	List<RstrImg> findByRstrIdOrderByIdAsc(@Param("rstrId")Long rstrId);
	
	@Query(value="select r from RstrImg r where r.id = :id and r.rstrId = :rstrId and r.status='Y'")
	Optional<RstrImg> findByIdAndRstrId(@Param("id")Long id, @Param("rstrId")Long rstrId);
}
