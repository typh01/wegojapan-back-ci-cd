package com.kh.avengers.admin.travels.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.admin.travels.model.dto.TravelOptionDTO;

@Mapper
public interface TravelOptionMapper {
    List<TravelOptionDTO> getOptionList(TravelOptionDTO optionDTO);
    List<TravelOptionDTO> getAdminOption(TravelOptionDTO optionDTO);
    int searchOption(long guNo);
    int insertOption(TravelOptionDTO optionDTO);
    int updateOption(TravelOptionDTO optionDTO);
    int deleteOption(long optionNo);
}

