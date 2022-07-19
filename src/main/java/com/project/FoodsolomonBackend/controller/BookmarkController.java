package com.project.FoodsolomonBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.FoodsolomonBackend.dto.BookmarkDto;
import com.project.FoodsolomonBackend.service.BookmarkService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookmarkController {

	private final BookmarkService bookmarkService;
	
	@GetMapping(value="/bookmark")
	public String getBookmarkYn(Long rstrId, Long memberId) {
		
		return bookmarkService.getBookmarkYn(rstrId, memberId);
	}
	
	@PostMapping(value="/bookmark")
	public Long saveBookmark(Long rstrId, Long memberId) {
		
		return bookmarkService.saveBookmark(rstrId, memberId);
	}
}
