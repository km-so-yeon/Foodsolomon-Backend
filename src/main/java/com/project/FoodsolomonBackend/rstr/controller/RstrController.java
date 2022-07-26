package com.project.FoodsolomonBackend.rstr.controller;

import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.SUCCESS;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.config.exception.BaseResponse;
import com.project.FoodsolomonBackend.rstr.dto.RstrDto;
import com.project.FoodsolomonBackend.rstr.dto.RstrFormDto;
import com.project.FoodsolomonBackend.rstr.service.RstrService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RstrController {
	
	private final RstrService rstrService;
	
	@GetMapping(value="/rstr")
	public RstrFormDto rstrDtl(Long id) {
		 
		RstrFormDto rstrFormDto = rstrService.getRstrDtl(id);
		
		return rstrFormDto;
	}
	
	@PostMapping(value="/rstr")
	public BaseResponse<Integer> saveRstr(@RequestBody RstrDto rstrDto) {
		
		rstrService.saveRstr(rstrDto);
		
		return new BaseResponse<>(SUCCESS);
	}
	
	@PostMapping(value="/rstrImg")
	public BaseResponse<Integer> saveRstrImg(Long rstrId, @RequestParam("rstrImgFile") List<MultipartFile> rstrImgList) {
		
		try {
			
			rstrService.saveRstrImg(rstrId, rstrImgList);
			
		} catch(BaseException exception) {
			
			return new BaseResponse<>(exception.getStatus());
		}
		
		return new BaseResponse<>(SUCCESS);
	}
}
