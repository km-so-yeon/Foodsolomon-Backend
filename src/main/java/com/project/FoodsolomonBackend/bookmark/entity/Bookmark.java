package com.project.FoodsolomonBackend.bookmark.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.project.FoodsolomonBackend.bookmark.dto.BookmarkDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="BOOKMARK")
@Getter @Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Bookmark {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="MEMBER_ID", nullable=false)
	private Long memberId;
	
	@Column(name="RSTR_ID", nullable=false)
	private Long rstrId;
	
	@Column(name="STATUS", nullable=false)
	private String status;
	
	@CreatedDate
	@Column(name="CREATED_TIME", nullable=false, updatable = false)
	private Date createdTime;
	
	@LastModifiedDate
	@Column(name="UPDATED_TIME", nullable=false)
	private Date updatedTime;
	
	public void updateBookmark(BookmarkDto bookmarkDto) {
		this.status = bookmarkDto.getStatus().equals("Y") ? "N" : "Y";
	}
}
