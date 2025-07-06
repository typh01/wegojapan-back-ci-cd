package com.kh.avengers.travels.model.dao;

import com.kh.avengers.travels.model.dto.UserTravelCategoryDTO;
import com.kh.avengers.travels.model.dto.UserTravelDTO;
import com.kh.avengers.travels.model.dto.UserTravelGuDTO;
import com.kh.avengers.travels.model.dto.UserTravelOptionDTO;
import com.kh.avengers.travels.model.dto.UserTravelTagDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserTravelMapper {
    List<UserTravelCategoryDTO> getCategoryList();
    List<UserTravelGuDTO> getGuList();
    List<UserTravelTagDTO> getTagList();
    List<UserTravelOptionDTO> getOptionList();
    List<UserTravelDTO> selectPagedTravelList(int offset, int size);
    int selectTotalTravelCount();
    UserTravelDTO selectTravelDetail(Long travelNo);
    List<UserTravelDTO> selectFilteredTravelList(Map<String, Object> filters);
    long countFilteredTravelList(Map<String, Object> filters);
}

