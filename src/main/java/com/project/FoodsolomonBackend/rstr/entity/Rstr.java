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
	
	@Column(name="PLACE_NAME", nullable=false)
	private String placeName;
	
	@Column(name="PHONE", nullable=false)
	private String phone;
	
	@Column(name="ADDRESS_NAME")
	private String addressName;
	
	@Column(name="ROAD_ADDRESS_NAME")
	private String roadAddressName;
	
	@Column(name="X", nullable=false)
	private float x;
	
	@Column(name="Y", nullable=false)
	private float y;
	
	@Column(name="CATEGORY_NAME", nullable=false)
	private String categoryName;
	
	@Column(name="PLACE_URL")
	private String placeUrl;
	
	@Column(name="STATUS", nullable=false)
	private String status;
	
	public void updateRstr(RstrDto rstrDto) {
		this.placeName = rstrDto.getPlaceName();
		this.phone = rstrDto.getPhone();
		this.addressName = rstrDto.getAddressName();
		this.roadAddressName = rstrDto.getRoadAddressName();
		this.x = rstrDto.getX();
		this.y = rstrDto.getY();
		this.categoryName = rstrDto.getCategoryName();
		this.placeUrl = rstrDto.getPlaceUrl();
		this.status = rstrDto.getStatus();
	}
}
