package com.kh.avengers.travles.model.service;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.travles.model.dto.TravelCityDTO;

public interface TravelCityService {

  RequestData getTravelCities(TravelCityDTO cityDTO);

  RequestData postTravelCity(TravelCityDTO cityDTO);

  RequestData updateTravelCity(TravelCityDTO cityDTO);

  RequestData deleteTravelCity(TravelCityDTO cityDTO);

}
