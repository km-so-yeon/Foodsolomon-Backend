package com.project.FoodsolomonBackend.rstr.entity;

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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="RSTR_IMG")
@Getter @Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class RstrImg {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "RSTR_ID", nullable=false)
	private Long rstrId;
	
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
	
	public void updateRstrImg(String imgUrl) {
		this.uriPath = imgUrl;
	}
	
	public void deleteRstrImg() {
		this.status = "N";
	}
}
