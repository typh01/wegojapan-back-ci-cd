package com.kh.avengers.plan.myPlanList.model.dao;

import com.kh.avengers.plan.myPlanList.model.dto.MyPlanListDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyPlanListMapper {

  /**
   * 사용자의 전체 플랜의 개수 조회
   * @param memberNo 사용자 고유식별 번호
   * @param status 플랜 상태("전체" || "예정" || "완료")
   * @param searchKeyword 검색 키워드
   * @return 위 조건에 맞는 플랜의 개수
   */
  Long countMyPlans(@Param("memberNo") Long memberNo,
                    @Param("status") String status,
                    @Param("searchKeyword") String searchKeyword);

  /**
   * 사용자의 플랜 목록 조회
   * @param memberNo 사용자 시퀀스 번호
   * @param status 플랜 상태("전체" || "예정" || "완료")
   * @param searchKeyword 검색 키워드
   * @param offset 시작 위치
   * @param limit 조회할 개수
   * @return 위 조건에 맞는 플랜의 개수
   */
  List<MyPlanListDto> selectMyPlans(@Param("memberNo") Long memberNo,
                                    @Param("status") String status,
                                    @Param("searchKeyword") String searchKeyword,
                                    @Param("offset") Integer offset,
                                    @Param("limit") Integer limit);

}
