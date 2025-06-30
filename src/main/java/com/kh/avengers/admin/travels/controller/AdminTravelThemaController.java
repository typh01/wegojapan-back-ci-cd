package com.kh.avengers.admin.travels.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kh.avengers.admin.travels.model.dto.TravelThemaDTO;
import com.kh.avengers.admin.travels.model.service.AdminTravelThemaService;
import com.kh.avengers.common.dto.RequestData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin/travels/thema")
@RequiredArgsConstructor
public class AdminTravelThemaController {
  private final AdminTravelThemaService travelThemaService;

  // 테마 목록 조회
  @GetMapping
  public ResponseEntity<RequestData> getTravelThemes(@ModelAttribute TravelThemaDTO themaDTO) {
    RequestData result = travelThemaService.getAdminThemes(themaDTO);
    return ResponseEntity.ok(result);
  }

  // 테마 등록
  @PostMapping
  public ResponseEntity<RequestData> postTravelTheme(@RequestBody TravelThemaDTO themaDTO) {
    RequestData result = travelThemaService.postAdminTheme(themaDTO);
    return ResponseEntity.ok(result);
  }

  // 테마 수정
  @PutMapping("/{themaNo}")
  public ResponseEntity<RequestData> updateTravelTheme(
    @PathVariable long themaNo,
    @RequestBody TravelThemaDTO themaDTO) {
    themaDTO.setThemaNo(themaNo);
    RequestData result = travelThemaService.updateAdminTheme(themaDTO);
    return ResponseEntity.ok(result);
  }

  // 테마 삭제 (다중)
  @DeleteMapping
  public ResponseEntity<RequestData> deleteTravelThemes(@RequestBody List<Long> themaNos) {
    RequestData result = travelThemaService.deleteAdminThemes(themaNos);
    return ResponseEntity.ok(result);
  }
}
