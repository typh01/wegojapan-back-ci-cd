package com.kh.avengers.travles.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.travles.model.dto.TravelCityDTO;

@Mapper
public interface TravelCityMapper {
    List<TravelCityDTO> selectTravelCityList(TravelCityDTO cityDTO);
    int searchCityNo(long cityNo);
    int insertTravelCity(TravelCityDTO cityDTO);
    int updateTravelCity(TravelCityDTO cityDTO);
    int deleteTravelCity(long cityNo);
}
