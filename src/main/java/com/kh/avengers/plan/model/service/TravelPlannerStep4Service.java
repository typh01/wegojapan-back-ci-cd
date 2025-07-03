package com.kh.avengers.plan.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep4Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep4Response;

public interface TravelPlannerStep4Service {
    
    /**
     * step4 플랜 업데이트 및 완료
     * @param request step4에서 입력받은 플랜 완성 정보
     * @param userDetails 인증된 사용자의 정보
     * @return
     */
    TravelPlannerStep4Response completeStep4Plan(TravelPlannerStep4Request request, CustomUserDetails userDetails);
}
