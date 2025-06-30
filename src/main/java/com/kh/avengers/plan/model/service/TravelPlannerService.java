package com.kh.avengers.plan.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep1Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep1Response;

public interface TravelPlannerService {
    
    /**
     * step1 플랜 생성 & 저장
     * @param request step1에서 입력받은 여행플랜의 기본 종보
     * @param userDetails 인증된 사용자 정보
     * @return
     */
    TravelPlannerStep1Response createStep1Plan(TravelPlannerStep1Request request, CustomUserDetails userDetails);
}
