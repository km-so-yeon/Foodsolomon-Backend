package com.project.FoodsolomonBackend.bookmark.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.FoodsolomonBackend.bookmark.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>{

	Optional<Bookmark> findByRstrIdAndMemberId(Long rstrId, Long memberId);
	
}
