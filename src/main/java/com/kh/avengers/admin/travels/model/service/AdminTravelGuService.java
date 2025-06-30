package com.kh.avengers.admin.travels.model.service;

import com.kh.avengers.admin.travels.model.dto.TravelGuDTO;
import com.kh.avengers.common.dto.RequestData;

public interface AdminTravelGuService {

  RequestData getTravelGus(TravelGuDTO guDTO);

  RequestData getAdminGus(TravelGuDTO guDTO);

  RequestData postAdminGus(TravelGuDTO guDTO);

  RequestData updateAdminGus(TravelGuDTO guDTO);
  
  RequestData deleteAdminGus(Long guNo);

}
