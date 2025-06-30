package com.kh.avengers.admin.travels.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.admin.travels.model.dto.TravelThemaDTO;


@Mapper
public interface TravelThemaMapper {
    List<TravelThemaDTO> getThemaList(TravelThemaDTO themaDTO);
    List<TravelThemaDTO> getAdminThemaList(TravelThemaDTO themaDTO);
    int searchThemaNo(long themaNo);
    int insertThema(TravelThemaDTO themaDTO);
    int updateThema(TravelThemaDTO themaDTO);
    int deleteThemas(List<Long> themaNos);
}

