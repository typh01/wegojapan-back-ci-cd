package com.kh.avengers.plan.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.avengers.plan.model.dto.TravelPlannerDto;

@Mapper
public interface TravelPlannerMapper {

    /**
     * 새로운 여행 플랜은 DB에 삽입
     * @param travelPlannerDto
     * @return 삽입된 행의 수 (성공 : 1, 실패 : 0)
     */
    int insertTravelPlan(TravelPlannerDto travelPlannerDto);

    /**
     * 플랜 번호로 여행 플랜을 조회
     * @param planNo 플랜의 고유 식별 번호
     * @return 여행 플랜 정보
     */
    TravelPlannerDto selectTravelPlanByPlanNo(@Param("planNo") Long planNo);

    /**
     * step2 정보로 여행 플랜 업데이트
     * @param travelPlannerDto 업데이트할 여행 플랜 정보
     * @return 업데이트된 행의 개수(성공 : 1, 실패 : 0)
     */
    int updateTravelPlanStep2(TravelPlannerDto travelPlannerDto);

    /**
     * step3 정보로 여행 플랜 업데이트
     * @param travelPlannerDto 업데이트할 여행 플랜 정보
     * @return 업데이트된 행의 개수(성공: 1, 실패: 0)
     */
    int updateTravelPlanStep3(TravelPlannerDto travelPlannerDto);

    /**
     * step4 정보로 여행 플랜 업데이트 및 완료
     * @param travelPlannerDto 업데이트 할 여행 플랜 정보
     * @return 업데이트된 행의 개수(성공 : 1, 실패 : 0)
     */
    int updateTravelPlanStep4(TravelPlannerDto travelPlannerDto);

}