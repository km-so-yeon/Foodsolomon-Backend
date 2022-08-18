package com.project.FoodsolomonBackend.review.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.project.FoodsolomonBackend.review.dto.ReviewFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="REVIEW")
@Getter @Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Review {

	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="RSTR_ID", nullable=false)
	private Long rstrId;
	
	@Column(name="WRITER_ID", nullable=false)
	private Long writerId;
	
	@Column(name="TITLE", nullable=false)
	private String title;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="STATUS", nullable=false)
	private String status;
	
	@Column(name="RATE", nullable=false)
	private int rate;
	
	@CreatedDate
	@Column(name="CREATED_TIME", nullable=false, updatable = false)
	private Date createdTime;
	
	@LastModifiedDate
	@Column(name="UPDATED_TIME", nullable=false)
	private Date updatedTime;
	
	public void updateReview(ReviewFormDto reviewFormDto) {
		this.title = reviewFormDto.getTitle();
		this.content = reviewFormDto.getContent();
		this.status = reviewFormDto.getStatus();
		this.rate = reviewFormDto.getRate();
	}
}
