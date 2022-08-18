package com.project.FoodsolomonBackend.rstr.service;

import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.POST_RSTRIMG_EMPTY_RSTR;
import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.FAILED_TO_FILEUPLOAD;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.rstr.dto.RstrDto;
import com.project.FoodsolomonBackend.rstr.dto.RstrFormDto;
import com.project.FoodsolomonBackend.rstr.dto.RstrImgDto;
import com.project.FoodsolomonBackend.rstr.entity.Rstr;
import com.project.FoodsolomonBackend.rstr.entity.RstrImg;
import com.project.FoodsolomonBackend.rstr.repository.RstrImgRepository;
import com.project.FoodsolomonBackend.rstr.repository.RstrRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RstrService {
	
	private final RstrRepository rstrRepository;
	
	private final RstrImgRepository rstrImgRepository;
	
	private final RstrImgService rstrImgService;
	
	public RstrFormDto getRstrDtl(Long id) {
		
		RstrFormDto rstrFormDto = new RstrFormDto();
		
		Optional<Rstr> oRstr = rstrRepository.findById(id);
		
		if (oRstr.isPresent()) {
			
			Rstr rstr = oRstr.get();
			rstrFormDto = RstrFormDto.of(rstr);
			
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
	
	public void saveRstr(RstrDto rstrDto) {
		
		Optional<Rstr> oRstr = rstrRepository.findById(rstrDto.getId());
		
		if (oRstr.isPresent()) {
			
			Rstr rstr = oRstr.get();
			rstr.updateRstr(rstrDto);
			
		} else {
			
			rstrRepository.save(rstrDto.createRstr());
		}
	}
	
	public void saveRstrImg(List<RstrImgDto> rstrImgDtoList, List<MultipartFile> rstrImgFileList) throws BaseException{
		
		Long rstrId = rstrImgDtoList.get(0).getRstrId();
		Optional<Rstr> oRstr = rstrRepository.findById(rstrId);
		
		if (oRstr.isPresent()) {
			
			try {
				
				for (int i = 0; i < rstrImgDtoList.size(); i++) {
					RstrImgDto rstrImgDto = rstrImgDtoList.get(i);
					MultipartFile rstrImgFile = rstrImgFileList.get(i);
					
					if (rstrImgDto.getStatus().equals("N")) {
						
						rstrImgService.deleteRstrImg(rstrImgDto.getId());
						
					} else if (rstrImgDto.getStatus().equals("Y")) {
						
						RstrImg rstrImg = rstrImgDto.createRstrImg();
						
						rstrImgService.saveRstrImg(rstrImg, rstrImgFile);
						
					}
				}
				
			} catch (Exception e) {
				
				throw new BaseException(FAILED_TO_FILEUPLOAD);
				
			}
			
		} else {
			
			throw new BaseException(POST_RSTRIMG_EMPTY_RSTR);
		}
		
	}
}
