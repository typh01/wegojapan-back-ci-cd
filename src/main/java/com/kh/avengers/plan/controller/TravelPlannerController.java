package com.kh.avengers.plan.controller;

import com.kh.avengers.plan.model.dto.SelectedPlaceDto;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep3Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep3Response;
import com.kh.avengers.plan.model.service.TravelPlannerStep3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep1Request;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep2Request;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep4Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep1Response;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep2Response;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep4Response;
import com.kh.avengers.plan.model.service.TravelPlannerStep1Service;
import com.kh.avengers.plan.model.service.TravelPlannerStep2Service;
import com.kh.avengers.plan.model.service.TravelPlannerStep4Service;
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
  private final TravelPlannerStep2Service travelPlannerStep2Service;
  private final TravelPlannerStep3Service travelPlannerStep3Service;
  private final TravelPlannerStep4Service travelPlannerStep4Service;
  private final ResponseUtil responseUtil;

  @PostMapping("/step1")
  public ResponseEntity<RequestData> createStep1Plan(
          @RequestBody @Valid TravelPlannerStep1Request request,
          @AuthenticationPrincipal CustomUserDetails userDetails) {

    log.info("여행 플래너 step1 생성 요청 >> 사용자 : {}, 시작일 : {}, 종료일 : {}, 여행인원 : {}",
            userDetails.getUsername(), request.getStartDate(), request.getEndDate(), request.getTravelers());

    TravelPlannerStep1Response response = travelPlannerStep1Service.createStep1Plan(request, userDetails);

    log.info("여행 플래너 step1 생성 완료!!! >> 플랜 번호 : {}", response.getPlanNo());

    RequestData result = responseUtil.rd("201", response, "여행 플래너 생성" );

    return ResponseEntity.status(201).body(result);
  }

  @PutMapping("/step2")
  public ResponseEntity<RequestData> updateStep2Plan(
          @RequestBody @Valid TravelPlannerStep2Request request,
          @AuthenticationPrincipal CustomUserDetails userDetails) {

    log.info("여행 플래너 step2 업데이트 요청 >> 시용자 : {}, 플랜번호 : {}, 선택지역 : {}", userDetails.getUsername(), request.getPlanNo(), request.getSelectedRegion());

    TravelPlannerStep2Response response = travelPlannerStep2Service.updateStep2Plan(request,userDetails);

    log.info("여행 플래너 step2 업데이트 완료!! >> 플랜번호 : {}, 선택지역 : {}",  response.getPlanNo(), response.getSelectedRegion());

    RequestData result = responseUtil.rd("200", response, "여행 지역 선택 완료!!!");

    return ResponseEntity.ok(result);
  }

//  @PutMapping("/step3")
//  public ResponseEntity<RequestData> updateStep3Plan(
//          @RequestBody @Valid TravelPlannerStep3Request request,
//          @AuthenticationPrincipal CustomUserDetails userDetails) {
//
//    log.info("여행 플래너 step3 업데이트 요청 >> 사용자: {}, 플랜번호: {}, 선택된 여행지 개수: {}",
//            userDetails.getUsername(), request.getPlanNo(), request.getSelectedPlaces().size());
//
//    TravelPlannerStep3Response response = travelPlannerStep3Service.updateStep3Plan(request, userDetails);
//
//    log.info("여행 플래너 step3 업데이트 완료!! >> 플랜번호: {}, 선택된 여행지 개수: {}",
//            response.getPlanNo(), response.getTotalSelectedCount());
//
//    RequestData result = responseUtil.rd("200", response, "여행지 선택 완료!!!");
//
//    return ResponseEntity.ok(result);
//  }

  // TravelPlannerController.java의 updateStep3Plan 메서드에 추가할 디버깅 코드

  @PutMapping("/step3")
  public ResponseEntity<RequestData> updateStep3Plan(
          @RequestBody @Valid TravelPlannerStep3Request request,
          @AuthenticationPrincipal CustomUserDetails userDetails) {

    // 🔍 디버깅: 받은 요청 데이터 전체 출력
    log.info("=== Step3 요청 데이터 디버깅 시작 ===");
    log.info("플랜번호: {}", request.getPlanNo());
    log.info("선택된 여행지 리스트 크기: {}",
            request.getSelectedPlaces() != null ? request.getSelectedPlaces().size() : "NULL");

    // 각 여행지 정보를 상세히 출력
    if (request.getSelectedPlaces() != null) {
      for (int i = 0; i < request.getSelectedPlaces().size(); i++) {
        SelectedPlaceDto place = request.getSelectedPlaces().get(i);
        log.info("여행지 [{}] - ID: {}, 이름: {}, 위도: {}, 경도: {}, 설명: {}",
                i + 1,
                place.getTravelId(),
                place.getTravelName(),
                place.getMapY(),
                place.getMapX(),
                place.getTravelDescription());
      }
    }
    log.info("=== Step3 요청 데이터 디버깅 끝 ===");

    log.info("여행 플래너 step3 업데이트 요청 >> 사용자: {}, 플랜번호: {}, 선택된 여행지 개수: {}",
            userDetails.getUsername(), request.getPlanNo(), request.getSelectedPlaces().size());

    TravelPlannerStep3Response response = travelPlannerStep3Service.updateStep3Plan(request, userDetails);

    log.info("여행 플래너 step3 업데이트 완료!! >> 플랜번호: {}, 선택된 여행지 개수: {}",
            response.getPlanNo(), response.getTotalSelectedCount());

    RequestData result = responseUtil.rd("200", response, "여행지 선택 완료!!!");

    return ResponseEntity.ok(result);
  }

  @PutMapping("/step4")
  public ResponseEntity<RequestData> completeStep4Plan(
          @RequestBody @Valid TravelPlannerStep4Request request,
          @AuthenticationPrincipal CustomUserDetails userDetails) {

    log.info("여행 플래너 step4 완료 요청 >> 사용자 : {}, 플랜번호 : {}, 제목 : {}",
            userDetails.getUsername(), request.getPlanNo(), request.getPlanTitle());

    TravelPlannerStep4Response response = travelPlannerStep4Service.completeStep4Plan(request, userDetails);

    log.info("여행 플래너 step4 완료!!! >> 플랜번호 : {}, 제목 : {}",
            response.getPlanNo(), response.getPlanTitle());

    RequestData result = responseUtil.rd("200", response, "여행 플랜 완료");

    return ResponseEntity.ok(result);
  }

}