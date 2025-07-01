package com.kh.avengers.plan.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep2Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep2Response;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class TravelPlannerStep2ServiceImpl implements TravelPlannerStep2Service {

    @Override
    @Transactional
    public TravelPlannerStep2Response updateStep2Plan(TravelPlannerStep2Request request, CustomUserDetails userDetails){
        
        log.info("여행플랜 step2 업데이트 시작!! >> 플랜번호 : {}, 사용자 : {}, 선택지역 : {}", request.getPlanNo(), userDetails.getMemberName(), request.getSelectedRegion());

        // 1. 플랜 번호로 기존 여행 플랜 조회

        
        // 2. 플랜이 존재하지 않는 경우


        // 3. 플랜의 소유자 확인(>> 본인의 플랜인지 확인)


        // 4. 업데이트할 데이터 준비


        // 5. DB에 step2 정보를 업데이트


        // 6, d업데이트 결과 확인


        // 7. 응답 DTO 생성


        return null;
    }
    
}
