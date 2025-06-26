package com.kh.avengers.travles.model.service;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.travles.model.dto.TravelGuDTO;

public interface TravelGuService {

  RequestData getTravelGus(TravelGuDTO guDTO);

  RequestData postTravelGus(TravelGuDTO guDTO);

  RequestData updateTravelGus(TravelGuDTO guDTO);
  
  RequestData deleteTravelGus(TravelGuDTO guDTO);

}
