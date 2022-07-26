package com.project.FoodsolomonBackend.rstr.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.project.FoodsolomonBackend.rstr.entity.Rstr;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RstrFormDto {

	private Long id;
	
	private String name;
	
	private String phone;
	
	private String address;
	
	private String roadAddress;
	
	private float latitue;
	
	private float hardness;
	
	private String categoryNm;
	
	private String plateUrl;
	
	private String status;
	
	private List<RstrImgDto> rstrImgDto = new ArrayList<>();
	
	private List<Long> rstrImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Rstr createRstr() {
		return modelMapper.map(this, Rstr.class);
	}
	
	public static RstrFormDto of(Rstr rstr) {
		return modelMapper.map(rstr, RstrFormDto.class);
	}
	
}
