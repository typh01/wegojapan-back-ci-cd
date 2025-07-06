package com.kh.avengers.reviews.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.reviews.model.dao.ReviewMapper;
import com.kh.avengers.util.ResponseUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

  private final ResponseUtil responseUtil;
  private final ReviewMapper reviewMapper;

  @Override
  public RequestData insertLike(Map<String, String> like) {

    Long likeCheck = reviewMapper.checkedLike(like);

    if(likeCheck == 1){
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

    if(likeCheck == 0){
      throw new InvalidException("좋아요를 이미 취소했습니다.");
    }

    Long likeCount = reviewMapper.deleteLikeCount(like);

    if(likeCount == 0){
      throw new InvalidException(("좋아요 취소 실패했습니다."));
    }
    return responseUtil.rd("200", null, "좋아요 취소했습니다.");
  }

}
