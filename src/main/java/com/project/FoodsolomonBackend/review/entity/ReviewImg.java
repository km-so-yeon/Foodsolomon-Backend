package com.project.FoodsolomonBackend.review.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="REVIEW_IMG")
@Getter @Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class ReviewImg {

	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name = "REVIEW_ID")
	private Long reviewId;
	
	@Column(name="URI_PATH", nullable=false)
	private String uriPath;
	
	@Column(name="STATUS", nullable=false)
	private String status;
	
	@CreatedDate
	@Column(name="CREATED_TIME", nullable=false, updatable = false)
	private Date createdTime;
	
	@LastModifiedDate
	@Column(name="UPDATED_TIME", nullable=false)
	private Date updatedTime;
	
	public void updateReviewImg(String imgUrl) {
		this.uriPath = imgUrl;
	}
	
}
