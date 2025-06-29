package com.kh.avengers.travles.controller;

import org.springframework.http.ResponseEntity;

import com.kh.avengers.admin.travels.model.dto.TravelCityDTO;
import com.kh.avengers.admin.travels.model.dto.TravelGuDTO;
import com.kh.avengers.admin.travels.model.service.AdminTravelCityService;
import com.kh.avengers.admin.travels.model.service.AdminTravelGuService;
import com.kh.avengers.common.dto.RequestData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TravelCityGuController {
  private final AdminTravelCityService travelCityService;
  private final AdminTravelGuService travelGuService;

  @GetMapping("/travels/city")
  public ResponseEntity<RequestData> getTravelCity(@ModelAttribute TravelCityDTO cityDTO) {
    RequestData result = travelCityService.getCities(cityDTO);
      return ResponseEntity.ok(result);
  }

  @GetMapping("/travels/gu")
  public ResponseEntity<RequestData> getTravelGus(@ModelAttribute TravelGuDTO guDTO) {
    RequestData result = travelGuService.getTravelGus(guDTO);
      return ResponseEntity.ok(result);
  }
}
