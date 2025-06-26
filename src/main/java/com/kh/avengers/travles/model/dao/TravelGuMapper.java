package com.kh.avengers.travles.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.travles.model.dto.TravelGuDTO;

@Mapper
public interface TravelGuMapper {
    List<TravelGuDTO> selectTravelGuList(TravelGuDTO guDTO);
    int searchGuNo(long guNo);
    int insertTravelGus(TravelGuDTO guDTO);
    int updateTravelGus(TravelGuDTO guDTO);
    int deleteTravelGus(long guNo);
}

