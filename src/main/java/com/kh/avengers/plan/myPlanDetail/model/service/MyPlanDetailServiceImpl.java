package com.kh.avengers.plan.myPlanDetail.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.myPlanDetail.model.dto.MyPlanDetailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class MyPlanDetailServiceImpl implements MyPlanDetailService{

  /**
   * 플랜 번호로 플랜 상세정보 조회
   * @param planNo 조회할 플랜 번호
   * @param userDetails 인증된 사용자의 정보
   * @return 플랜 상세 정보
   */
  @Override
  public MyPlanDetailDto getPlanDetail(Long planNo, CustomUserDetails userDetails){

    log.info("내플랜 상세 조회!! >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);

    // 1. 플랜 상세 정보 조회(>> 본인 플랜인지 확인)


    // 2. 플랜 존재x || 접근권한X


    // 3. 플랜에 연결된 선택된 여행지 목록 조회


    // 4. 플랜 상세 정보에 선택된 여행지 목록 설정


    return null;
  }

}
