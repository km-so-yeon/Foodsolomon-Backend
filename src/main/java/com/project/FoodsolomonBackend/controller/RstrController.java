package com.project.FoodsolomonBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.FoodsolomonBackend.dto.RstrDto;
import com.project.FoodsolomonBackend.dto.RstrFormDto;
import com.project.FoodsolomonBackend.service.RstrService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RstrController {
	
	private final RstrService rstrService;
	
	@GetMapping("/rstr")
	public RstrFormDto rstrDtl(Long id) {
		 
		RstrFormDto rstrFormDto = rstrService.getRstrDtl(id);
		
		return rstrFormDto;
	}
}
