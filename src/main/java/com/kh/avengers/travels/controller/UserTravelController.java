package com.kh.avengers.travels.controller;

import org.springframework.http.ResponseEntity;

import com.kh.avengers.admin.travels.model.dto.TravelCategoryDTO;
import com.kh.avengers.admin.travels.model.dto.TravelCityDTO;
import com.kh.avengers.admin.travels.model.dto.TravelGuDTO;
import com.kh.avengers.admin.travels.model.service.AdminTravelCategoryService;
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
@RequestMapping("/api/travels")
@RequiredArgsConstructor
public class UserTravelController {
  private final AdminTravelCityService travelCityService;
  private final AdminTravelGuService travelGuService;
  private final AdminTravelCategoryService travelCategoryService;

  @GetMapping("/city")
  public ResponseEntity<RequestData> getTravelCity(@ModelAttribute TravelCityDTO cityDTO) {
    RequestData result = travelCityService.getCities(cityDTO);
      return ResponseEntity.ok(result);
  }

  @GetMapping("/gu")
  public ResponseEntity<RequestData> getTravelGus(@ModelAttribute TravelGuDTO guDTO) {
    RequestData result = travelGuService.getTravelGus(guDTO);
      return ResponseEntity.ok(result);
  }

  @GetMapping("/category")
  public ResponseEntity<RequestData> getTravelCategories(@ModelAttribute TravelCategoryDTO categoryDTO) {
      RequestData result = travelCategoryService.getTravelCategories(categoryDTO);
      return ResponseEntity.ok(result);
  }
}
