package com.kh.avengers.travles.controller;

import org.springframework.http.ResponseEntity;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.travles.model.dto.TravelCityDTO;
import com.kh.avengers.travles.model.service.TravelCityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TravelCityController {
  private final TravelCityService travelCityService;

  @GetMapping("/travels/city")
  public ResponseEntity<RequestData> getTravelCity(@ModelAttribute TravelCityDTO cityDTO) {
    RequestData result = travelCityService.getTravelCities(cityDTO);
      return ResponseEntity.ok(result);
  }

  @PostMapping("/admin/travels/city")
  public ResponseEntity<RequestData> postTravelCity(@RequestBody TravelCityDTO cityDTO) {
    RequestData result = travelCityService.postTravelCity(cityDTO);
      return ResponseEntity.ok(result);
  }

  @PutMapping("/admin/travels/city")
  public ResponseEntity<RequestData> updateTravelCity(@RequestBody TravelCityDTO cityDTO) {
    RequestData result = travelCityService.updateTravelCity(cityDTO);
      return ResponseEntity.ok(result);
  }

  @DeleteMapping("/admin/travels/city/{cityNo}")
  public ResponseEntity<RequestData> deleteTravelCity(@RequestBody TravelCityDTO cityDTO) {
    RequestData result = travelCityService.deleteTravelCity(cityDTO);
      return ResponseEntity.ok(result);
  }
  
}
