package com.kh.avengers.plan.model.dao;

import com.kh.avengers.plan.model.dto.TravelChoiceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TravelChoiceMapper {

  /**
   * 여행지 선택 정보를 DB에 삽입
   * @param travelChoiceDto 삽입할 선캑한 여행지 정보
   * @return 삽입된 행의 개수(성공 : 1, 실패 : 0)
   */
  int insertTravelChoice(TravelChoiceDto travelChoiceDto);

  /**
   * 플랜 번호에 해당하는 모든 선택된 여행지 정보 삭제
   * @param planNo 플랜 번호
   * @return 삭제된 행의 개수
   */
  int deleteTravelChoices(@Param("planNo") Long planNo);

}
