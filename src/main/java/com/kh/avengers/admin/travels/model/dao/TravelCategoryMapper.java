package com.kh.avengers.admin.travels.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.admin.travels.model.dto.TravelCategoryDTO;

@Mapper
public interface TravelCategoryMapper {
    List<TravelCategoryDTO> getCategoryList(TravelCategoryDTO categoryDTO);
    List<TravelCategoryDTO> getAdminCategory(TravelCategoryDTO categoryDTO);
    int searchCategoryNo(long categoryNo);
    int insertCategory(TravelCategoryDTO categoryDTO);
    int updateCategory(TravelCategoryDTO categoryDTO);
    int deleteCategories(List<Long> categoryNo);
}

