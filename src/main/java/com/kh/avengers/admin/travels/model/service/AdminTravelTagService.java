package com.kh.avengers.admin.travels.model.service;

import com.kh.avengers.admin.travels.model.dto.TravelTagDTO;
import com.kh.avengers.common.dto.RequestData;

import java.util.List;

public interface AdminTravelTagService {

  RequestData getTravelTags(TravelTagDTO tagDTO);
    
  RequestData getAdminTags(TravelTagDTO tagDTO);

  RequestData postAdminTag(TravelTagDTO tagDTO);

  RequestData updateAdminTag(TravelTagDTO tagDTO);

  RequestData deleteAdminTags(List<Long> tagNos);

}
