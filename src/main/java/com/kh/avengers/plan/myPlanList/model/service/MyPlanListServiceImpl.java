package com.kh.avengers.plan.myPlanList.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.myPlanList.model.dao.MyPlanListMapper;
import com.kh.avengers.plan.myPlanList.model.dto.MyPlanListDto;
import com.kh.avengers.plan.myPlanList.model.dto.request.MyPlanListRequest;
import com.kh.avengers.plan.myPlanList.model.dto.response.MyPlanListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyPlanListServiceImpl implements MyPlanListService{

  private final MyPlanListMapper myPlanListMapper;

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
    Integer offset = (request.getPage() - 1) * request.getSize();
    
    // 2. 전체 플랜 개수
    Long totalElements = myPlanListMapper.countMyPlans(userDetails.getMemberNo(), request.getStatus(), request.getSearchKeyword());
    log.info("나의 조건에 맞는 전체 플랜의 개수 : {}", totalElements);
    
    // 3. 전체 페이지 수 계산
    Integer totalPages = (int)Math.ceil((double) totalElements / request.getSize());
    
    // 4. 플랜목록 조회
    List<MyPlanListDto> plans = myPlanListMapper.selectMyPlans(
            userDetails.getMemberNo(),
            request.getStatus(),
            request.getSearchKeyword(),
            offset,
            request.getSize()
    );

    log.info("조회된 플랜의 개수 : {}", plans.size());
    
    // 5. 다음페이지/이전페이지가 존재하는지 계산
    Boolean hasNext = request.getPage() < totalPages; // 현재 페이지가 마지막 페이지보다 작으면 다음 페이지 존재
    Boolean hasPrevious = request.getPage() > 1; // 현재 페이지가 1보다 크면 이전 페이지 존재

    // 6. 응답 객체
    MyPlanListResponse response = MyPlanListResponse.builder()
            .plans(plans)
            .currentPage(request.getPage())
            .totalPages(totalPages)
            .totalElements(totalElements)
            .pageSize(request.getSize())
            .hasNext(hasNext)
            .hasPrevious(hasPrevious)
            .build();

    log.info("내 플랜 목록 조회 완료 >> 사용자 : {}, 조회된 항목 : {}, 전체 : {}, 페이지 : {}/{}",
            userDetails.getMemberName(), plans.size(), totalElements,
            request.getPage(), totalPages);

    return response;
  }

}
