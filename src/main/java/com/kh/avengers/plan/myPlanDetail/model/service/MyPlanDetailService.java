package com.kh.avengers.plan.myPlanDetail.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.myPlanDetail.model.dto.MyPlanDetailDto;

public interface MyPlanDetailService {

  /**
   * 플랜 번호로 플랜 상세정보 조회
   * @param planNo 조회할 플랜 번호
   * @param userDetails 인증된 사용자의 정보
   * @return 플랜 상세 정보
   */
  MyPlanDetailDto getPlanDetail(Long planNo, CustomUserDetails userDetails);

  /**
   * 플랜 상세정보 수정
   * @param planDetailDto 플랜의 상세정보
   * @param userDetails 인증된 사용자 정보
   * @return 수정된 플랜
   */
  MyPlanDetailDto updatePlanDetail(MyPlanDetailDto planDetailDto, CustomUserDetails userDetails);

  /**
   * 플랜 삭제
   * @param planNo 삭제할 플랜의 고유 식별 번호
   * @param userDetails 인증된 사용자의 정보
   * @return 삭제 성공 여부
   */
  boolean deletePlanDetail(Long planNo, CustomUserDetails userDetails);

}
