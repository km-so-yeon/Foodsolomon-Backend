package com.project.FoodsolomonBackend.review.service;

import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.FAILED_TO_FILEUPLOAD;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.review.dto.ReviewFormDto;
import com.project.FoodsolomonBackend.review.dto.ReviewImgDto;
import com.project.FoodsolomonBackend.review.entity.Review;
import com.project.FoodsolomonBackend.review.entity.ReviewImg;
import com.project.FoodsolomonBackend.review.repository.ReviewImgRepository;
import com.project.FoodsolomonBackend.review.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
	
	private final ReviewImgRepository reviewImgRepository;
	
	private final ReviewImgService reviewImgService;
	
	public List<ReviewFormDto> getReviewDtl(Long rstrId) {
		
		List<Review> reviewList = reviewRepository.findByRstrIdOrderByIdAsc(rstrId);
		List<ReviewFormDto> reviewFormDtoList = new ArrayList<>();
		
		for (Review review : reviewList) {
			
			ReviewFormDto reviewFormDto = ReviewFormDto.of(review);
			
			Long reviewId = reviewFormDto.getId();
			List<ReviewImg> reviewImgList = reviewImgRepository.findByReviewIdOrderByIdAsc(reviewId);
			List<ReviewImgDto> reviewImgDtoList = new ArrayList<>();
			
			for (ReviewImg reviewImg : reviewImgList) {
				ReviewImgDto reviewImgDto = ReviewImgDto.of(reviewImg);
				reviewImgDtoList.add(reviewImgDto);
			}
			
			reviewFormDto.setReviewImgDto(reviewImgDtoList);
			reviewFormDtoList.add(reviewFormDto);
		}
		
		return reviewFormDtoList;
	}
	
	public void saveReview(ReviewFormDto reviewFormDto, List<MultipartFile> reviewImgFileList) throws BaseException {
		
		Review review = reviewFormDto.createReview();
		reviewRepository.save(review);
		
		try {
			
			for(MultipartFile reviewImgFile : reviewImgFileList) {
				ReviewImg reviewImg = new ReviewImg();
				reviewImg.setReviewId(review.getId());
				
				reviewImgService.saveReviewImg(reviewImg, reviewImgFile);
			}
			
		} catch (Exception e) {
			throw new BaseException(FAILED_TO_FILEUPLOAD);
		}
		
	}
	
	public void updateReview(ReviewFormDto reviewFormDto, List<MultipartFile> reviewImgFileList) throws BaseException {
		
		Review review = new Review();
		Optional<Review> oReview = reviewRepository.findById(reviewFormDto.getId());
		
		if(oReview.isPresent()) {
			
			review = oReview.get();
			review.updateReview(reviewFormDto);
			List<Long> itemImgIds = reviewFormDto.getReviewImgIds();
			
			try {
				
				for(int i = 0; i < itemImgIds.size(); i++) {
					reviewImgService.updateReviewImg(itemImgIds.get(i), reviewImgFileList.get(i));
				}
				
			} catch (Exception e) {
				throw new BaseException(FAILED_TO_FILEUPLOAD);
			}
		}
	}
	
}
