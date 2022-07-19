package com.project.FoodsolomonBackend.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.FoodsolomonBackend.dto.BookmarkDto;
import com.project.FoodsolomonBackend.entity.Bookmark;
import com.project.FoodsolomonBackend.repository.BookmarkRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

	private final BookmarkRepository bookmarkRepository;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public String getBookmarkYn(Long rstrId, Long memberId) {
		
		String result = "N";
		
		Optional<Bookmark> bookmark = bookmarkRepository.findByRstrIdAndMemberId(rstrId, memberId);
		
		if(bookmark.isPresent()) {
			BookmarkDto bookmarkDto = BookmarkDto.of(bookmark.get());
			result = bookmarkDto.getStatus();
		}
		
		return result;
	}
	
	public Long saveBookmark(Long rstrId, Long memberId) {
		
		Optional<Bookmark> bookmark = bookmarkRepository.findByRstrIdAndMemberId(rstrId, memberId);
		BookmarkDto bookmarkDto = new BookmarkDto();
		
		if(bookmark.isPresent()) {
			
			bookmarkDto = BookmarkDto.of(bookmark.get());
			bookmark.get().updateBookmark(bookmarkDto);
			
		} else {
			
			// memberRepository.findById
			// 없을 경우 null return..
			
			bookmarkDto.setRstrId(rstrId);
			bookmarkDto.setMemberId(memberId);
			bookmarkRepository.save(bookmarkDto.createBookmark());
			
		}
		
		return bookmarkDto.getId();
	}
}
