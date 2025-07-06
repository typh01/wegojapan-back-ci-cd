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
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/my-plans")
public class MyPlanDetailController {

  private final MyPlanDetailService myPlanDetailService;
  private final ResponseUtil responseUtil;
  
  // 플랜 상세정보 조회
  @GetMapping("/{planNo}")
  public ResponseEntity<RequestData> getPlanDetail(
          @PathVariable Long planNo,
          @AuthenticationPrincipal CustomUserDetails userDetails) {
    log.info("플랜 상세 조회 요청! >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);

    try {
      MyPlanDetailDto planDetail = myPlanDetailService.getPlanDetail(planNo, userDetails);
      log.info("플랜 상세조회 성공!!! >> 사용자 : {}, 플랜번호 : {}, 플랜제목 : {}", userDetails.getMemberName(), planNo, planDetail.getPlanTitle());
      RequestData result = responseUtil.rd("200", planDetail, "플랜 상세 조회 성공!!!!");
      return ResponseEntity.ok(result);
    } catch (ForbiddenException e) {
      log.warn("플랜 상세 조회 실패 >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);
      RequestData errorResult = responseUtil.rd("404", null, e.getMessage());
      return ResponseEntity.status(404).body(errorResult);
    }
  }

  // 플랜 상세정보 수정
  @PutMapping("/{planNo}")
  public ResponseEntity<RequestData> updatePlanDetail(
          @PathVariable Long planNo,
          @RequestBody MyPlanDetailDto planDetailDto,
          @AuthenticationPrincipal CustomUserDetails userDetails) {
    log.info("플랜 수정 요청! >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);

    try {
      planDetailDto.setPlanNo(planNo);

      MyPlanDetailDto updatedPlan = myPlanDetailService.updatePlanDetail(planDetailDto, userDetails);
      log.info("플랜 수정 성공!!! >> 사용자 : {}, 플랜번호 : {}, 플랜제목 : {}", userDetails.getMemberName(), planNo, updatedPlan.getPlanTitle());

      RequestData result = responseUtil.rd("200", updatedPlan, "플랜 수정 성공!!!!");
      return ResponseEntity.ok(result);

    } catch (ForbiddenException e) {
      log.warn("플랜 수정 실패 >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);
      RequestData errorResult = responseUtil.rd("403", null, e.getMessage());
      return ResponseEntity.status(403).body(errorResult);
    }
  }

  // 플랜 삭제
  @DeleteMapping("/{planNo}")
  public ResponseEntity<RequestData> deletePlanDetail(
          @PathVariable Long planNo,
          @AuthenticationPrincipal CustomUserDetails userDetails) {
    log.info("플랜 삭제 요청! >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);

    try {
      boolean isDeleted = myPlanDetailService.deletePlan(planNo, userDetails);

      if (isDeleted) {
        log.info("플랜 삭제 성공!!!!! >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);

        RequestData result = responseUtil.rd("200", null, "플랜 삭제 성공!!!!");
        return ResponseEntity.ok(result);

      } else {
        log.warn("플랜 삭제 실패 >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);
        RequestData errorResult = responseUtil.rd("400", null, "플랜 삭제에 실패!");
        return ResponseEntity.status(400).body(errorResult);
      }

    } catch (ForbiddenException e) {
      log.warn("플랜 삭제 실패 >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);
      RequestData errorResult = responseUtil.rd("403", null, e.getMessage());
      return ResponseEntity.status(403).body(errorResult);
    }
  }
}
