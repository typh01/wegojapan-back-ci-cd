package com.kh.avengers.admin.travels.controller;

import org.springframework.http.ResponseEntity;

import com.kh.avengers.admin.travels.model.dto.TravelGuDTO;
import com.kh.avengers.admin.travels.model.service.AdminTravelGuService;
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
@RequestMapping("/api/admin/travels/gu")
@RequiredArgsConstructor
public class AdminTravelGuController {
  private final AdminTravelGuService travelGuService;

  @GetMapping
  public ResponseEntity<RequestData> getTravelGus(@ModelAttribute TravelGuDTO guDTO) {
    RequestData result = travelGuService.getAdminGus(guDTO);
      return ResponseEntity.ok(result);
  }

  @PostMapping
  public ResponseEntity<RequestData> postTravelGus(@RequestBody TravelGuDTO guDTO) {
    RequestData result = travelGuService.postAdminGus(guDTO);
      return ResponseEntity.ok(result);
  }

  @PutMapping("/{guNo}")
  public ResponseEntity<RequestData> updateTravelGus(
    @PathVariable long guNo,
    @RequestBody TravelGuDTO guDTO) {
      guDTO.setGuNo(guNo);
    RequestData result = travelGuService.updateAdminGus(guDTO);
      return ResponseEntity.ok(result);
  }

  @DeleteMapping("/{guNo}")
  public ResponseEntity<RequestData> deleteTravelGus(@PathVariable Long guNo) {
    RequestData result = travelGuService.deleteAdminGus(guNo);
      return ResponseEntity.ok(result);
  }
  
}
