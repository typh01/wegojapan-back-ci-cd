package com.kh.avengers.travles.controller;

import org.springframework.http.ResponseEntity;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.travles.model.dto.TravelGuDTO;
import com.kh.avengers.travles.model.service.TravelGuService;

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
@RequestMapping("/api")
@RequiredArgsConstructor
public class TravelGuController {
  private final TravelGuService travelGuService;

  @GetMapping("/travels/gu")
  public ResponseEntity<RequestData> getTravelGus(@ModelAttribute TravelGuDTO guDTO) {
    RequestData result = travelGuService.getTravelGus(guDTO);
      return ResponseEntity.ok(result);
  }

  @PostMapping("/admin/travels/gu")
  public ResponseEntity<RequestData> postTravelGus(@RequestBody TravelGuDTO guDTO) {
    RequestData result = travelGuService.postTravelGus(guDTO);
      return ResponseEntity.ok(result);
  }

  @PutMapping("/admin/travels/gu")
  public ResponseEntity<RequestData> updateTravelGus(@RequestBody TravelGuDTO guDTO) {
    RequestData result = travelGuService.updateTravelGus(guDTO);
      return ResponseEntity.ok(result);
  }

  @DeleteMapping("/admin/travels/gu/{guNo}")
  public ResponseEntity<RequestData> deleteTravelGus(@RequestBody TravelGuDTO guDTO) {
    RequestData result = travelGuService.deleteTravelGus(guDTO);
      return ResponseEntity.ok(result);
  }
  
}
