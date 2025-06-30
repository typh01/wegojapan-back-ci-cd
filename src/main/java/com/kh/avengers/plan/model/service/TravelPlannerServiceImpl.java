package com.kh.avengers.plan.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep1Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep1Response;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class TravelPlannerServiceImpl implements TravelPlannerService {

    /**
     * step1 플랜 생성 & 저장
     * @param request step1에서 입력받은 여행플랜의 기본 종보
     * @param userDetails 인증된 사용자 정보
     * @return
     */
    @Override
    @Transactional
    public TravelPlannerStep1Response createStep1Plan(TravelPlannerStep1Request request, CustomUserDetails useDetails){


        return null;

    }
    
}
