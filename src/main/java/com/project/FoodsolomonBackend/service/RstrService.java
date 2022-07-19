package com.project.FoodsolomonBackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.FoodsolomonBackend.dto.RstrFormDto;
import com.project.FoodsolomonBackend.dto.RstrImgDto;
import com.project.FoodsolomonBackend.entity.Rstr;
import com.project.FoodsolomonBackend.entity.RstrImg;
import com.project.FoodsolomonBackend.repository.RstrImgRepository;
import com.project.FoodsolomonBackend.repository.RstrRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RstrService {
	
	private final RstrRepository rstrRepository;
	
	private final RstrImgRepository rstrImgRepository;
	
	public RstrFormDto getRstrDtl(Long id) {
		
		RstrFormDto rstrFormDto = new RstrFormDto();
		
		Optional<Rstr> rstr = rstrRepository.findById(id);
		
		if (rstr.isPresent()) {
			
			rstrFormDto = RstrFormDto.of(rstr.get());
			
			List<RstrImg> rstrImgList = rstrImgRepository.findByRstrIdOrderByIdAsc(id);
			List<RstrImgDto> rstrImgDtoList = new ArrayList<>();
			
			for (RstrImg rstrImg : rstrImgList) {
				RstrImgDto rstrImgDto = RstrImgDto.of(rstrImg);
				rstrImgDtoList.add(rstrImgDto);
			}
			
			rstrFormDto.setRstrImgDto(rstrImgDtoList);
		}
		
		return rstrFormDto;
	}
}
