package com.kh.avengers.admin.travels.model.service;

import java.util.List;

import com.kh.avengers.admin.travels.model.dto.TravelThemaDTO;
import com.kh.avengers.common.dto.RequestData;

public interface AdminTravelThemaService {

  RequestData getTravelThemes(TravelThemaDTO themaDTO);

  RequestData getAdminThemes(TravelThemaDTO themaDTO);

  RequestData postAdminTheme(TravelThemaDTO themaDTO);

  RequestData updateAdminTheme(TravelThemaDTO themaDTO);

  RequestData deleteAdminThemes(List<Long> themaNos);

}
