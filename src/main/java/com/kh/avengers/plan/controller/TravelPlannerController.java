package com.kh.avengers.plan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep1Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep1Response;
import com.kh.avengers.plan.model.service.TravelPlannerStep1Service;
import com.kh.avengers.util.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/travel-planner")
public class TravelPlannerController {

    private final TravelPlannerStep1Service travelPlannerStep1Service;
    private final ResponseUtil responseUtil;
    
    @PostMapping("/step1")
    public ResponseEntity<RequestData> createStep1Plan(
        @RequestBody @Valid TravelPlannerStep1Request request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {

            log.info("여행 플래너 step1 생성 요청 >> 사용자 : {}, 시작일 : {}, 종료일 : {}, 여행인원 : {}",
                    userDetails.getUsername(), request.getStartDate(), request.getEndDate(), request.getTravelers());

            TravelPlannerStep1Response response = travelPlannerStep1Service.createStep1Plan(request, userDetails);

            log.info("여행 플래너 step1 생성 완료!!! >> 플랜 번호 : {}", response.getPlanNo());

            RequestData result = responseUtil.rd("201", response, "여행 플래너" );

            return ResponseEntity.status(201).body(result);
        }
    

}
