package com.kh.avengers.admin.travels.model.service;

import java.util.List;

import com.kh.avengers.admin.travels.model.dto.TravelCategoryDTO;
import com.kh.avengers.common.dto.RequestData;

public interface AdminTravelCategoryService {

  RequestData getTravelCategories(TravelCategoryDTO categoryDTO);

  RequestData getAdminCategories(TravelCategoryDTO categoryDTO);

  RequestData postAdminCategories(TravelCategoryDTO categoryDTO);

  RequestData updateAdminCategory(TravelCategoryDTO categoryDTO);

  RequestData deleteAdminCategories(List<Long> guNo);

}
