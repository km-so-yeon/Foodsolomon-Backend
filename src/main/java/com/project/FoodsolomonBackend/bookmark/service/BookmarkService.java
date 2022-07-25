package com.project.FoodsolomonBackend.bookmark.service;

import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.POST_BOOKMARK_EMPTY_MEMBER;
import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.SUCCESS;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.FoodsolomonBackend.bookmark.dto.BookmarkDto;
import com.project.FoodsolomonBackend.bookmark.entity.Bookmark;
import com.project.FoodsolomonBackend.bookmark.repository.BookmarkRepository;
import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.config.exception.BaseResponse;
import com.project.FoodsolomonBackend.user.model.User;
import com.project.FoodsolomonBackend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

	private final BookmarkRepository bookmarkRepository;
	
	private final UserRepository userRepository;
	
	public String getBookmarkYn(Long rstrId, Long memberId) {
		
		String result = "N";
		
		Optional<Bookmark> bookmark = bookmarkRepository.findByRstrIdAndMemberId(rstrId, memberId);
		
		if(bookmark.isPresent()) {
			BookmarkDto bookmarkDto = BookmarkDto.of(bookmark.get());
			result = bookmarkDto.getStatus();
		}
		
		return result;
	}
	
	public void saveBookmark(Long rstrId, Long memberId) throws BaseException {
		
		Optional<Bookmark> oBookmark = bookmarkRepository.findByRstrIdAndMemberId(rstrId, memberId);
		BookmarkDto bookmarkDto = new BookmarkDto();
		
		if(oBookmark.isPresent()) {
			
			Bookmark bookmark = oBookmark.get();
			bookmarkDto = BookmarkDto.of(bookmark);
			bookmark.updateBookmark(bookmarkDto);
			
		} else {
			
			Optional<User> user = userRepository.findById(memberId);
			
			if(user.isPresent()) {
				
				bookmarkDto.setRstrId(rstrId);
				bookmarkDto.setMemberId(memberId);
				bookmarkDto.setStatus("Y");
				bookmarkRepository.save(bookmarkDto.createBookmark());
				
			} else {
				
				throw new BaseException(POST_BOOKMARK_EMPTY_MEMBER);
				
			}
			
		}
	}
}
