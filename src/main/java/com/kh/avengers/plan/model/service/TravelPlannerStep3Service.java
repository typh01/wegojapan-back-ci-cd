package com.kh.avengers.plan.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep3Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep3Response;

public interface TravelPlannerStep3Service {

  /**
   * step3 플랜 업데이트
   * @param request step3에서 선택한 여행지 정보
   * @param userDetails 인증된 사용자의 정보
   * @return 응답 정보
   */
  TravelPlannerStep3Response updateStep3Plan(TravelPlannerStep3Request request, CustomUserDetails userDetails);

}
