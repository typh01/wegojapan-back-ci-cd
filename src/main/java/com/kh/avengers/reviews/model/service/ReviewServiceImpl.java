package com.kh.avengers.reviews.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.avengers.exception.commonexception.NotFoundException;
import com.kh.avengers.exception.commonexception.UpdateException;
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

  /**
   * 특정 여행지의 리뷰목록 조회
   * @param travelNo 여행지 번호
   * @param offset 시작위치
   * @param limit 조회할 개수
   * @return 여행지의 리뷰 목록
   */
  @Override
  public RequestData getTravelReviews(Long travelNo, int offset, int limit, Long currentMemberNo){

    log.info("여행지의 리뷰 목록 조회 시작 >> 여행지번허: {}, offset: {}, limit: {}, 현재사용자: {}", travelNo, offset, limit, currentMemberNo);

    try{
      // 1. 리뷰 조회
      List<ReviewDTO> reviewList = reviewMapper.selectTravelReviews(travelNo, offset, limit, currentMemberNo);
      log.info("리뷰 조회 완료 >> 조회된 리뷰의 수 : {}", reviewList.size());

      // 2. 각 리뷰의 이미지 목록 조회
      for(ReviewDTO review : reviewList){
        try{
          List<ReviewImageDTO> imageList = reviewMapper.selectReviewImages(review.getReviewNo());
          review.setImageList(imageList);
          log.debug("리뷰 이미지 조회 완료 >> 리뷰번호 : {}, 이미지 수 : {}, 좋아요여부 : {}, 좋아요수 : {}",
                  review.getReviewNo(), imageList.size(), review.getIsLiked(), review.getLikeCount());
        } catch (Exception e) {
          log.warn("리뷰 이미지 조회 실패 >> 리뷰번호: {}", review.getReviewNo(), e);
          // 이미지 조회 실패해도 리뷰는 표시
          review.setImageList(List.of());
        }
      }

      // 3. 전체 리뷰 개수 조회
      long totalCount = reviewMapper.countTravelReviews(travelNo);
      log.info("전체 리뷰 개수: {}", totalCount);

      // 4. 평균 별점 조회
      Double averageRating = reviewMapper.selectAverageRating(travelNo);
      if (averageRating == null) {
        averageRating = 0.0;
      }
      log.info("평균 별점 >> {}", averageRating);

      // 5. 응답 데이터 구성
      Map<String, Object> responseData = new HashMap<>();
      responseData.put("reviews", reviewList);
      responseData.put("totalCount", totalCount);
      responseData.put("averageRating", averageRating);

      return responseUtil.rd("200", responseData, "여행지 리뷰 목록 조회 완료");

    } catch (Exception e) {
      log.error("여행지 리뷰 목록 조회 실패! >>  여행지번호 : {}", travelNo, e);
      throw new RuntimeException("리뷰 목록을 불러오는 중 오류가 발생했습니다.", e);
    }
  }

  /**
   * 특정 회원의 리뷰 목록 조회
   * @param memberNo 회원 번호
   * @return 리뷰 목록
   */
  @Override
  public RequestData getMemberReviews(Long memberNo) {

    log.info("회원 리뷰 목록 조회 시작 >> 회원번호: {}", memberNo);

    try {
      // 1. 특정 회원의 모든 리뷰 목록 조회
      List<ReviewDTO> reviewList = reviewMapper.selectMemberReviews(memberNo);
      log.info("회원의 리뷰 조회 완료 >> 조회된 리뷰 수: {}", reviewList.size());

      // 2. 각 리뷰의 이미지 목록 조회
      for (ReviewDTO review : reviewList) {
        try {
          List<ReviewImageDTO> imageList = reviewMapper.selectReviewImages(review.getReviewNo());
          review.setImageList(imageList);
        } catch (Exception e) {
          log.warn("회원 리뷰 이미지 조회 실패 >> 리뷰번호: {}", review.getReviewNo(), e);
          review.setImageList(List.of());
        }
      }

      return responseUtil.rd("200", reviewList, "내 리뷰 목록 조회 완료!!!");

    } catch (Exception e) {
      log.error("회원 리뷰 목록 조회 실패 >> 회원번호: {}", memberNo, e);
      throw new RuntimeException("내 리뷰 목록을 불러오는 중 오류가 발생했습니다.", e);
    }
  }


  /**
   * 리뷰 수정
   * @param reviewDTO 수정할 리뷰 정보
   * @param images 새로 추가할 이미지 목록
   * @param deletedImageNos 삭제할 이미지 번호 목록
   * @return 수정 결과
   */
  @Override
  @Transactional
  public RequestData updateReview(ReviewDTO reviewDTO, List<MultipartFile> images, List<Long> deletedImageNos) {

    log.info("리뷰 수정 시작 >>  리뷰번호 : {}", reviewDTO.getReviewNo());

    // 1. 리뷰 존재 여부 확인
    ReviewDTO existingReview = reviewMapper.selectReviewByNo(reviewDTO.getReviewNo());
    if (existingReview == null) {
      throw new NotFoundException("해당 리뷰를 찾을 수 없습니다.");
    }

    // 2. 리뷰 정보 수정
    int result = reviewMapper.updateReview(reviewDTO);
    if (result <= 0) {
      throw new UpdateException("리뷰 수정에 실패했습니다.");
    }

    // 3. 삭제할 이미지가 있으면 삭제
    if (deletedImageNos != null && !deletedImageNos.isEmpty()) {
      for (Long imageNo : deletedImageNos) {
        reviewMapper.deleteReviewImage(imageNo);
      }
    }

    // 4. 새로운 이미지가 있으면 S3에 업로드 후 DB 저장
    if (images != null && !images.isEmpty()) {
      for (MultipartFile image : images) {
        if (!image.isEmpty()) {
          try {
            // S3에 이미지 업로드
            String imageUrl = s3Service.upload(image);

            // DB에 이미지 정보 저장
            ReviewImageDTO imageDTO = ReviewImageDTO.builder()
                    .reviewNo(reviewDTO.getReviewNo())
                    .imageUrl(imageUrl)
                    .build();

            reviewMapper.insertReviewImage(imageDTO);
          } catch (Exception e) {
            log.error("리뷰 수정 중 이미지 업로드 실패", e);
          }
        }
      }
    }

    log.info("리뷰 수정 완료 >> 리뷰번호: {}", reviewDTO.getReviewNo());
    return responseUtil.rd("200", reviewDTO.getReviewNo(), "리뷰 수정이 완료되었습니다!!!");

  }

  /**
   * 리뷰 삭제
   * @param reviewNo 삭제할 리뷰 번호
   * @return 삭제 결과
   */
  @Override
  @Transactional
  public RequestData deleteReview(Long reviewNo) {
    log.info("리뷰 삭제 시작 >> 리뷰번호: {}", reviewNo);

    // 1. 리뷰 존재 여부 확인
    ReviewDTO existingReview = reviewMapper.selectReviewByNo(reviewNo);
    if (existingReview == null) {
      throw new NotFoundException("해당 리뷰를 찾을 수 없습니다.");
    }

    // 2. 리뷰 이미지 먼저 삭제
    reviewMapper.deleteReviewImagesByReviewNo(reviewNo);

    // 3. 리뷰 삭제
    int result = reviewMapper.deleteReview(reviewNo);
    if (result <= 0) {
      throw new UpdateException("리뷰 삭제에 실패했습니다.");
    }

    log.info("리뷰 삭제 완료 >>  리뷰번호: {}", reviewNo);
    return responseUtil.rd("200", reviewNo, "리뷰 삭제가 완료되었습니다.");

  }
}
