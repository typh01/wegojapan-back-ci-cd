package com.kh.avengers.admin.travels.model.service;

import java.util.List;

import com.kh.avengers.admin.travels.model.dto.TravelOptionDTO;
import com.kh.avengers.common.dto.RequestData;

public interface AdminTravelOptionService {

  RequestData getTravelOptions(TravelOptionDTO optionDTO);
  
  RequestData getAdminOptions(TravelOptionDTO optionDTO);

  RequestData postAdminOption(TravelOptionDTO optionDTO);

  RequestData updateAdminOption(TravelOptionDTO optionDTO);

  RequestData deleteAdminOptions(List<Long> optionNos);

}
