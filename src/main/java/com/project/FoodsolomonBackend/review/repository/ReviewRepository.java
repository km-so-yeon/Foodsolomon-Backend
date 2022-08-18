package com.project.FoodsolomonBackend.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.FoodsolomonBackend.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	@Query(value="select r from Review r where r.rstrId = :rstrId and r.status = 'Y'")
	List<Review> findByRstrIdOrderByIdAsc(@Param("rstrId")Long rstrId);
	
}
