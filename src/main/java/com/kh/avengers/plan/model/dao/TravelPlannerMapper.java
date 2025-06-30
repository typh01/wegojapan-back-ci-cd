package com.kh.avengers.plan.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.plan.model.dto.TravelPlannerDto;

@Mapper
public interface TravelPlannerMapper {

    /**
     * 새로운 여행 플랜은 DB에 삽입
     * @param travelPlannerDto
     * @return 삽입된 행의 수 (성공 : 1, 실패 : 0)
     */
    int insertTravelPlan(TravelPlannerDto travelPlannerDto);
    
}
