package com.kh.avengers.plan.myPlanList.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.myPlanList.model.dto.request.MyPlanListRequest;
import com.kh.avengers.plan.myPlanList.model.dto.response.MyPlanListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class MyPlanListServiceImpl implements MyPlanListService{

  /**
   * 사용자의 플랜 목록 조회
   * @param request 조회 조건(페이징, 상태필터, 검색키워드)
   * @param userDetails 인증된 사용자의 정보
   * @return 페이징된 플랜 목록
   */
  @Override
  public MyPlanListResponse getMyPlanList(MyPlanListRequest request, CustomUserDetails userDetails){

    log.info("내 플랜 목록 조회 시작!! >> 사용자 : {}, 페이지 : {}, 크기 : {}, 상태 : {}, 검색어 : {}",
            userDetails.getMemberName(), request.getPage(), request.getSize(), request.getStatus(), request.getSearchKeyword());

    // 1. offset 계산
     
    
    // 2. 전체 플랜 개수
    
    
    // 3. 전체 페이지 수 계산
    
    
    // 4. 플랜목록 조회
    
    
    // 5. 다음페이지/이전페이지가 존재하는지 계산
    
    
    // 6. 응답 객체
    

    return null;

  }

}
