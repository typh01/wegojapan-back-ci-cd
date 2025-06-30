package com.kh.avengers.admin.travels.controller;

import org.springframework.http.ResponseEntity;

import com.kh.avengers.admin.travels.model.dto.TravelCityDTO;
import com.kh.avengers.admin.travels.model.service.AdminTravelCityService;
import com.kh.avengers.common.dto.RequestData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/admin/travels/city")
@RequiredArgsConstructor
public class AdminTravelCityController {
  private final AdminTravelCityService adminTravelCityService;

  @GetMapping
  public ResponseEntity<RequestData> getAdminTravelCity(@ModelAttribute TravelCityDTO cityDTO) {
    RequestData result = adminTravelCityService.getAdminCities(cityDTO);
      return ResponseEntity.ok(result);
  }

  
  @PostMapping
  public ResponseEntity<RequestData> postTravelCity(@RequestBody TravelCityDTO cityDTO) {
    RequestData result = adminTravelCityService.postCity(cityDTO);
      return ResponseEntity.ok(result);
  }

  @PutMapping("/{cityNo}")
  public ResponseEntity<RequestData> updateTravelCity(
      @PathVariable Long cityNo,
      @RequestBody TravelCityDTO cityDTO) {
      cityDTO.setCityNo(cityNo);
      RequestData result = adminTravelCityService.updateCity(cityDTO);
      return ResponseEntity.ok(result);
  }

  @DeleteMapping("/{cityNo}")
  public ResponseEntity<RequestData> deleteTravelCity(@PathVariable Long cityNo) {
    RequestData result = adminTravelCityService.deleteCity(cityNo);
      return ResponseEntity.ok(result);
  }
  
}
