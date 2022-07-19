package com.project.FoodsolomonBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.FoodsolomonBackend.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>{

	Optional<Bookmark> findByRstrIdAndMemberId(Long rstrId, Long memberId);
	
}
