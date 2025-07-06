package com.kh.avengers.reviews.model.service;

import java.util.List;
import java.util.Map;

import com.kh.avengers.reviews.model.dto.ReviewDTO;
import com.kh.avengers.reviews.model.dto.ReviewImageDTO;
import com.kh.avengers.travels.model.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.reviews.model.dao.ReviewMapper;
import com.kh.avengers.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

  private final ResponseUtil responseUtil;
  private final ReviewMapper reviewMapper;
  private final S3Service s3Service;

  @Override
  public RequestData insertLike(Map<String, String> like) {

    Long likeCheck = reviewMapper.checkedLike(like);

    if (likeCheck == 1) {
      throw new InvalidException("좋아요를 이미 눌렀습니다.");
    }

    Long likeCount = reviewMapper.insertLikeCount(like);

    if (likeCount == 0) {
      throw new InvalidException("좋아요 등록에 실패했습니다.");
    }
    return responseUtil.rd("200", null, "좋아요를 눌렀습니다.");
  }

  @Override
  public RequestData deleteLike(Map<String, String> like) {
    Long likeCheck = reviewMapper.checkedLike(like);

    if (likeCheck == 0) {
      throw new InvalidException("좋아요를 이미 취소했습니다.");
    }

    Long likeCount = reviewMapper.deleteLikeCount(like);

    if (likeCount == 0) {
      throw new InvalidException(("좋아요 취소 실패했습니다."));
    }
    return responseUtil.rd("200", null, "좋아요 취소했습니다.");
  }

  /**
   * 리뷰 작성
   *
   * @param reviewDTO 리뷰 정보
   * @param images    첨부하는 이미지 목록
   * @return 작성된 리뷰 고유 식별번호
   */
  @Override
  @Transactional
  public RequestData createReview(ReviewDTO reviewDTO, List<MultipartFile> images) {

    log.info("리뷰 작성 시작 >> 여행지번호: {}, 회원번호: {}", reviewDTO.getTravelNo(), reviewDTO.getMemberNo());

    // 1. 리뷰 기본 정보 DB에 등록
    int result = reviewMapper.insertReview(reviewDTO);
    if (result <= 0) {
      throw new InvalidException("리뷰 등록에 실패했습니다.");
    }

    log.info("리뷰 등록 완료 >> 리뷰번호: {}", reviewDTO.getReviewNo());

    // 2. 이미지가 있으면 S3에 업로드 후 DB 저장
    if (images != null && !images.isEmpty()) {
      log.info("이미지 업로드 시작!! >> 이미지 개수: {}", images.size());

      for (MultipartFile image : images) {
        if (!image.isEmpty()) {
          try {
            // S3에 이미지 업로드
            String imageUrl = s3Service.upload(image);
            log.info("S3 업로드 완료!! >>  URL: {}", imageUrl);

            // DB에 이미지 정보 저장
            ReviewImageDTO imageDTO = ReviewImageDTO.builder()
                    .reviewNo(reviewDTO.getReviewNo())
                    .imageUrl(imageUrl)
                    .build();

            int imageResult = reviewMapper.insertReviewImage(imageDTO);
            if (imageResult <= 0) {
              log.warn("이미지 정보 DB 저장 실패ㅠ >>  URL: {}", imageUrl);
            }
          } catch (Exception e) {
            log.error("이미지 업로드 실패", e);
            // 이미지 업로드 실패해도 리뷰 작성은 완료되도록 처리
          }
        }
      }
    }
    return responseUtil.rd("200", reviewDTO.getReviewNo(), "리뷰 작성이 완료되었습니다.");
  }

  @Override
  public RequestData getTravelReviews(Long travelNo, int offset, int limit){



    return null;

  }
}
