package com.kh.avengers.admin.travels.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kh.avengers.admin.travels.model.dto.TravelOptionDTO;
import com.kh.avengers.admin.travels.model.service.AdminTravelOptionService;
import com.kh.avengers.common.dto.RequestData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin/travels/option")
@RequiredArgsConstructor
public class AdminTravelOptionController {
  private final AdminTravelOptionService travelOptionService;

  // 옵션 목록 조회
  @GetMapping
  public ResponseEntity<RequestData> getTravelOptions(@ModelAttribute TravelOptionDTO optionDTO) {
    RequestData result = travelOptionService.getAdminOptions(optionDTO);
    return ResponseEntity.ok(result);
  }

  // 옵션 등록
  @PostMapping
  public ResponseEntity<RequestData> postTravelOption(@RequestBody TravelOptionDTO optionDTO) {
    RequestData result = travelOptionService.postAdminOption(optionDTO);
    return ResponseEntity.ok(result);
  }

  // 옵션 수정
  @PutMapping("/{optionNo}")
  public ResponseEntity<RequestData> updateTravelOption(
    @PathVariable long optionNo,
    @RequestBody TravelOptionDTO optionDTO) {
    optionDTO.setOptionNo(optionNo);
    RequestData result = travelOptionService.updateAdminOption(optionDTO);
    return ResponseEntity.ok(result);
  }

  // 옵션 삭제 (다중)
  @DeleteMapping
  public ResponseEntity<RequestData> deleteTravelOptions(@RequestBody List<Long> optionNos) {
    RequestData result = travelOptionService.deleteAdminOptions(optionNos);
    return ResponseEntity.ok(result);
  }
}
