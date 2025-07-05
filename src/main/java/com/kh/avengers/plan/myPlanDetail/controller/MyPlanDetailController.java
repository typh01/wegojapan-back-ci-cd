package com.kh.avengers.plan.myPlanDetail.controller;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.util.ForbiddenException;
import com.kh.avengers.plan.myPlanDetail.model.dto.MyPlanDetailDto;
import com.kh.avengers.plan.myPlanDetail.model.service.MyPlanDetailService;
import com.kh.avengers.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/my-plans")
public class MyPlanDetailController {

  private final MyPlanDetailService myPlanDetailService;
  private final ResponseUtil responseUtil;

  @GetMapping("/{planNo}")
  public ResponseEntity<RequestData> getPlanDetail(
          @PathVariable Long planNo,
          @AuthenticationPrincipal CustomUserDetails userDetails) {
    log.info("플랜 상세 조회 요청! >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);

    try{
      MyPlanDetailDto planDetail = myPlanDetailService.getPlanDetail(planNo, userDetails);
      log.info("플랜 상세조회 성공!!! >> 사용자 : {}, 플랜번호 : {}, 플랜제목 : {}", userDetails.getMemberName(), planNo, planDetail.getPlanTitle());
      RequestData result = responseUtil.rd("200", planDetail, "플랜 상세 조회 성공!!!!");
      return ResponseEntity.ok(result);
    } catch (ForbiddenException e){
      log.warn("플랜 상세 조회 실패 >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);
      RequestData errorResult = responseUtil.rd("404", null, e.getMessage());
      return ResponseEntity.status(404).body(errorResult);
    }
  }
}
