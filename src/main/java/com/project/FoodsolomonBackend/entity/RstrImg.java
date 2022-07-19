package com.project.FoodsolomonBackend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="RSTR_IMG")
@Getter @Setter
@ToString
public class RstrImg {

	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="RSTR_ID", nullable=false)
	private Long rstrId;
	
	@Column(name="URI_PATH", nullable=false)
	private String uriPath;
	
	@Column(name="STATUS", nullable=false)
	private String status;
	
	@Column(name="CREATED_TIME", nullable=false)
	private Date createdTime;
	
	@Column(name="UPDATED_TIME", nullable=false)
	private Date updatedTime;
	
}
