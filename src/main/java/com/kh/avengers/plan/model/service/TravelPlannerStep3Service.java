package com.kh.avengers.plan.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep3Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep3Response;

public interface TravelPlannerStep3Service {

  TravelPlannerStep3Response updateStep3Plan(TravelPlannerStep3Request request, CustomUserDetails customUserDetails);

}
