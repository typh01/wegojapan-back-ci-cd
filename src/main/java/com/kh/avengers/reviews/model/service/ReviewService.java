package com.kh.avengers.reviews.model.service;

import java.util.Map;

import com.kh.avengers.common.dto.RequestData;

public interface ReviewService {

  RequestData insertLike(Map<String, String> like);

  RequestData deleteLike(Map<String,String> like);

}
