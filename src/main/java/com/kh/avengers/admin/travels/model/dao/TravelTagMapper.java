package com.kh.avengers.admin.travels.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.admin.travels.model.dto.TravelTagDTO;

@Mapper
public interface TravelTagMapper {
  List<TravelTagDTO> getTagList(TravelTagDTO tagDTO);
  List<TravelTagDTO> getAdminTagList(TravelTagDTO tagDTO);
  int searchTagNo(long tagNo);
  int insertTag(TravelTagDTO tagDTO);
  int updateTag(TravelTagDTO tagDTO);
  int deleteTags(List<Long> tagNos);
}
