package com.project.FoodsolomonBackend.rstr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.FoodsolomonBackend.rstr.dto.RstrDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="RSTR")
@Getter @Setter
@ToString
public class Rstr {
	
	@Id
	@Column(name="ID", nullable=false)
	private Long id;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@Column(name="PHONE", nullable=false)
	private String phone;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="ROAD_ADDRESS")
	private String roadAddress;
	
	@Column(name="LATITUE", nullable=false)
	private float latitue;
	
	@Column(name="HARDNESS", nullable=false)
	private float hardness;
	
	@Column(name="CATEGORY_NM", nullable=false)
	private String categoryNm;
	
	@Column(name="PLACE_URL")
	private String placeUrl;
	
	@Column(name="STATUS", nullable=false)
	private String status;
	
	public void updateRstr(RstrDto rstrDto) {
		this.name = rstrDto.getName();
		this.phone = rstrDto.getPhone();
		this.address = rstrDto.getAddress();
		this.roadAddress = rstrDto.getRoadAddress();
		this.latitue = rstrDto.getLatitue();
		this.hardness = rstrDto.getHardness();
		this.categoryNm = rstrDto.getCategoryNm();
		this.placeUrl = rstrDto.getPlateUrl();
		this.status = rstrDto.getStatus();
	}
}
