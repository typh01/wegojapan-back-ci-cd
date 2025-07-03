package com.kh.avengers.plan.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep2Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep2Response;

public interface TravelPlannerStep2Service {

    /**
     * step2플랜 업데이트(선택한 지역 저장하기)
     * @param request  step2에서 정한 지역 정보
     * @param userDetails 인증된 사용자의 정보
     * @return 응답 정보
     */
    TravelPlannerStep2Response updateStep2Plan(TravelPlannerStep2Request request, CustomUserDetails userDetails);
    
}
