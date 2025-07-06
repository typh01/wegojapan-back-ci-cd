package com.kh.avengers.plan.myPlanDetail.model.dao;

import com.kh.avengers.plan.model.dto.SelectedPlaceDto;
import com.kh.avengers.plan.myPlanDetail.model.dto.MyPlanDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyPlanDetailMapper {

  /**
   * 플랜번호와 사용자 고유식별 번호로 플랜 상세 정보 조회
   * @param planNo 플랜 고유 식별 번호
   * @param memberNo 사용자 고유 식별 번호
   * @return 플랜 내용의 상세 정보
   */
  MyPlanDetailDto selectPlanDetailByPlanNoAndMemberNo(@Param("planNo") Long planNo,
                                                      @Param("memberNo") Long memberNo);

  /**
   * 플랜 번호로 선택된 여행지 목록 조회
   * @param planNo 플랜 고유 식별 번호
   * @return 선택된 여행지 목록
   */
  List<SelectedPlaceDto> selectSelectedPlacesByPlanNo(@Param("planNo") Long planNo);

  /**
   * 플랜 상세 정보 수정
   * @param planDetailDto 플랜 상세 정보
   * @return 수정된 행의 개수
   */
  int updatePlanDetail(MyPlanDetailDto planDetailDto);

  /**
   * 플랜에 연결된 선택된 여행지 목록 삭제
   * @param planNo 플랜 고유 식별 번호
   * @return 삭제된 행의 개수
   */
  int deleteSelectedPlacesByPlanNo(@Param("planNo") Long planNo);

  /**
   * 여행지 선택 삽입
   * @param selectedPlace 삽입할 여행지 정보
   * @return 삽입된 행의 개수
   */
  int insertSelectedPlace(SelectedPlaceDto selectedPlace);

  /**
   * 플랜 삭제
   * @param planNo 삭제할 플랜 고유 식별 번호
   * @param memberNo 사용자 고유 식별 번호
   * @return 삭제된 행의 개수
   */
  int deletePlan(@Param("planNo") Long planNo, @Param("memberNo") Long memberNo);
}