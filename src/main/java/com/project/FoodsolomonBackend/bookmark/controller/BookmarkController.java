package com.project.FoodsolomonBackend.bookmark.controller;

import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.SUCCESS;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.FoodsolomonBackend.bookmark.service.BookmarkService;
import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.config.exception.BaseResponse;

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
	public BaseResponse<Integer> saveBookmark(Long rstrId, Long memberId) {
		
		try {
			
			bookmarkService.saveBookmark(rstrId, memberId);
			
		} catch (BaseException exception) {
			
			return new BaseResponse<>(exception.getStatus());
		}
		
		return new BaseResponse<>(SUCCESS);
	}
}
