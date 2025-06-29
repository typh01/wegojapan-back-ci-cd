package com.kh.avengers.admin.travels.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.admin.travels.model.dto.TravelGuDTO;

@Mapper
public interface TravelGuMapper {
    List<TravelGuDTO> getGuList(TravelGuDTO guDTO);
    List<TravelGuDTO> getAdminGu(TravelGuDTO guDTO);
    int searchGuNo(long guNo);
    int insertGu(TravelGuDTO guDTO);
    int updateGu(TravelGuDTO guDTO);
    int deleteGu(long guNo);
}

