package com.kh.avengers.plan.myPlanList.controller;

import com.kh.avengers.plan.myPlanList.model.dto.request.MyPlanListRequest;
import com.kh.avengers.plan.myPlanList.model.dto.response.MyPlanListResponse;
import com.kh.avengers.plan.myPlanList.model.service.MyPlanListService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.common.dto.RequestData;

import com.kh.avengers.util.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/my-plans")
public class MyPlanListController {

  private final MyPlanListService myPlanListService;
  private final ResponseUtil responseUtil;

  /**
   * 내 플랜 목록 조회
   * @param page 페이지 번호 (기본값: 1)
   * @param size 페이지 크기 (기본값: 3)
   * @param status 플랜 상태 필터 (전체 || 예정 || 완료)
   * @param searchKeyword 검색 키워드
   * @param userDetails 인증된 사용자 정보
   * @return 페이징된 플랜 목록 응답
   */
  @GetMapping
  public ResponseEntity<RequestData> getMyPlanList(
          @RequestParam(defaultValue = "1") Integer page,
          @RequestParam(defaultValue = "3") Integer size,
          @RequestParam(required = false) String status,         // 상태 필터 (선택사항)
          @RequestParam(required = false) String searchKeyword,  // 검색 키워드 (선택사항)
          @AuthenticationPrincipal CustomUserDetails userDetails) {

    log.info("내 플랜 목록 조회 요청 >> 사용자 : {}, 페이지 : {}, 크기 : {}, 상태 : {}, 검색어 : {}",
            userDetails.getUsername(), page, size, status, searchKeyword);

    MyPlanListRequest request = MyPlanListRequest.builder()
            .page(page)
            .size(size)
            .status(status)
            .searchKeyword(searchKeyword)
            .build();

    MyPlanListResponse response = myPlanListService.getMyPlanList(request, userDetails);

    log.info("내 플랜 목록 조회 완료 >> 사용자 : {}, 조회된 항목 : {}, 전체 : {}",
            userDetails.getUsername(), response.getPlans().size(), response.getTotalElements());

    RequestData result = responseUtil.rd("200", response, "내 플랜 목록 조회 성공");

    return ResponseEntity.ok(result);
  }

}