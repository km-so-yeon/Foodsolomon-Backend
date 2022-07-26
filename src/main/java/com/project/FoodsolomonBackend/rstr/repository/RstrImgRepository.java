package com.project.FoodsolomonBackend.rstr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.FoodsolomonBackend.rstr.entity.RstrImg;

public interface RstrImgRepository extends JpaRepository<RstrImg, Long>{

	List<RstrImg> findByRstrIdOrderByIdAsc(Long rstrId);
}
