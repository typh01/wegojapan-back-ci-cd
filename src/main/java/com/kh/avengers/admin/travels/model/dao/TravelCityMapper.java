package com.kh.avengers.admin.travels.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.admin.travels.model.dto.TravelCityDTO;

@Mapper
public interface TravelCityMapper {
    List<TravelCityDTO> selectCityList(TravelCityDTO cityDTO);
    List<TravelCityDTO> selectCity(TravelCityDTO cityDTO);
    int searchCityNo(long cityNo);
    int insertCity(TravelCityDTO cityDTO);
    int updateCity(TravelCityDTO cityDTO);
    int deleteCity(long cityNo);
}
