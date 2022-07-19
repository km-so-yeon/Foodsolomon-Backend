package com.project.FoodsolomonBackend.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.project.FoodsolomonBackend.entity.Bookmark;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookmarkDto {

	private Long id;
	
	private Long memberId;
	
	private Long rstrId;
	
	private String status;
	
	private Date createdTime;
	
	private Date updatedTime;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Bookmark createBookmark() {
		return modelMapper.map(this, Bookmark.class);
	}
	
	public static BookmarkDto of(Bookmark bookmark) {
		return modelMapper.map(bookmark, BookmarkDto.class);
	}
}
