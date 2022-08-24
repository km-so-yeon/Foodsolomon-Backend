package com.project.FoodsolomonBackend.rstr.dto;

import org.modelmapper.ModelMapper;

import com.project.FoodsolomonBackend.rstr.entity.Rstr;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RstrDto {

	private Long id;
	
	private String placeName;
	
	private String phone;
	
	private String addressName;
	
	private String roadAddressName;
	
	private float x;
	
	private float y;
	
	private String categoryName;
	
	private String placeUrl;
	
	private String status;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Rstr createRstr() {
		return modelMapper.map(this, Rstr.class);
	}
}
