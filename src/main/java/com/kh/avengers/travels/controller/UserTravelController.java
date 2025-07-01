package com.kh.avengers.travels.controller;

import org.springframework.http.ResponseEntity;

import com.kh.avengers.admin.travels.model.dto.TravelCategoryDTO;
import com.kh.avengers.admin.travels.model.dto.TravelCityDTO;
import com.kh.avengers.admin.travels.model.dto.TravelGuDTO;
import com.kh.avengers.admin.travels.model.service.AdminTravelCategoryService;
import com.kh.avengers.admin.travels.model.service.AdminTravelCityService;
import com.kh.avengers.admin.travels.model.service.AdminTravelGuService;
import com.kh.avengers.admin.travels.model.service.AdminTravelService;
import com.kh.avengers.common.dto.RequestData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
  private final AdminTravelService adminTravelService;

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

    // 여행지 전체 목록 조회
    @GetMapping
    public ResponseEntity<RequestData> getTravelList() {
        log.info("여행지 전체 목록 조회 요청");
        return ResponseEntity.ok(adminTravelService.getTravelList());
    }

    // 여행지 상세 조회
    @GetMapping("/{travelNo}")
    public ResponseEntity<RequestData> getTravelDetail(@PathVariable Long travelNo) {
        log.info("여행지 상세 조회 요청 - travelNo: {}", travelNo);
        return ResponseEntity.ok(adminTravelService.getTravelDetail(travelNo));
    }
}
