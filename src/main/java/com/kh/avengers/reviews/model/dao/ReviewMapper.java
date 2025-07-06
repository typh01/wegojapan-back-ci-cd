package com.kh.avengers.reviews.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

  Long checkedLike(Map<String, String> like);

  Long insertLikeCount(Map<String, String> like);

  Long deleteLikeCount(Map<String,String> like);

  Long getLikeCount(Map<String,String> like);

}
