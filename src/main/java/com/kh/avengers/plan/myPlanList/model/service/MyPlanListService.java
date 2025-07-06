package com.kh.avengers.plan.myPlanList.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.myPlanList.model.dto.request.MyPlanListRequest;
import com.kh.avengers.plan.myPlanList.model.dto.response.MyPlanListResponse;

public interface MyPlanListService {

  /**
   * 나의 플랜 목록 조회
   * @param request 조회 조건(페이징, 상태필터, 검색키워드)
   * @param userDetails 인증된 사용자의 정보
   * @return 페이징된 플랜의 목록
   */
  MyPlanListResponse getMyPlanList(MyPlanListRequest request, CustomUserDetails userDetails);

}
