package com.project.FoodsolomonBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.FoodsolomonBackend.entity.ReviewImg;

public interface ReviewImgRepository extends JpaRepository<ReviewImg, Long>{

	@Query(value="select r from ReviewImg r where r.reviewId = :reviewId and r.status='Y'")
	List<ReviewImg> findByReviewIdOrderByIdAsc(@Param("reviewId")Long reviewId);
	
}
