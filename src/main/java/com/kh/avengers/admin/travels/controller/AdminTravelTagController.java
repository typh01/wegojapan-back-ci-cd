package com.kh.avengers.admin.travels.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kh.avengers.admin.travels.model.dto.TravelTagDTO;
import com.kh.avengers.admin.travels.model.service.AdminTravelTagService;
import com.kh.avengers.common.dto.RequestData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin/travels/tag")
@RequiredArgsConstructor
public class AdminTravelTagController {
  private final AdminTravelTagService travelTagService;

  // 태그 목록 조회
  @GetMapping
  public ResponseEntity<RequestData> getTravelTags(@ModelAttribute TravelTagDTO tagDTO) {
    RequestData result = travelTagService.getAdminTags(tagDTO);
    return ResponseEntity.ok(result);
  }

  // 태그 등록
  @PostMapping
  public ResponseEntity<RequestData> postTravelTag(@RequestBody TravelTagDTO tagDTO) {
    RequestData result = travelTagService.postAdminTag(tagDTO);
    return ResponseEntity.ok(result);
  }

  // 태그 수정
  @PutMapping("/{tagNo}")
  public ResponseEntity<RequestData> updateTravelTag(
    @PathVariable long tagNo,
    @RequestBody TravelTagDTO tagDTO) {
    tagDTO.setTagNo(tagNo);
    RequestData result = travelTagService.updateAdminTag(tagDTO);
    return ResponseEntity.ok(result);
  }

  // 태그 삭제 (다중)
  @DeleteMapping
  public ResponseEntity<RequestData> deleteTravelTags(@RequestBody List<Long> tagNos) {
    RequestData result = travelTagService.deleteAdminTags(tagNos);
    return ResponseEntity.ok(result);
  }
}
