package com.kh.avengers.admin.travels.model.service;

import com.kh.avengers.admin.travels.model.dto.TravelCityDTO;
import com.kh.avengers.common.dto.RequestData;

public interface AdminTravelCityService {

  RequestData getCities(TravelCityDTO cityDTO);

  RequestData getAdminCities(TravelCityDTO cityDTO);

  RequestData postCity(TravelCityDTO cityDTO);

  RequestData updateCity(TravelCityDTO cityDTO);

  RequestData deleteCity(Long cityNo);

}
