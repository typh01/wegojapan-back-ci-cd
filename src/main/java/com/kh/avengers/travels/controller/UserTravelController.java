package com.kh.avengers.travels.controller;

import org.springframework.http.ResponseEntity;

import com.kh.avengers.admin.travels.model.dto.TravelCategoryDTO;
import com.kh.avengers.admin.travels.model.dto.TravelCityDTO;
import com.kh.avengers.admin.travels.model.dto.TravelGuDTO;
import com.kh.avengers.admin.travels.model.dto.TravelOptionDTO;
import com.kh.avengers.admin.travels.model.dto.TravelTagDTO;
import com.kh.avengers.admin.travels.model.dto.TravelThemaDTO;
import com.kh.avengers.admin.travels.model.service.AdminTravelCategoryService;
import com.kh.avengers.admin.travels.model.service.AdminTravelCityService;
import com.kh.avengers.admin.travels.model.service.AdminTravelGuService;
import com.kh.avengers.admin.travels.model.service.AdminTravelOptionService;
import com.kh.avengers.admin.travels.model.service.AdminTravelService;
import com.kh.avengers.admin.travels.model.service.AdminTravelTagService;
import com.kh.avengers.admin.travels.model.service.AdminTravelThemaService;
import com.kh.avengers.common.dto.RequestData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  private final AdminTravelTagService adminTravelTagService;
  private final AdminTravelOptionService adminTravelOptionService;
  private final AdminTravelThemaService travelThemaService;

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

  @GetMapping("/tags")
  public ResponseEntity<RequestData> getTravelTags(@ModelAttribute TravelTagDTO tagDTO) {
      RequestData result = adminTravelTagService.getTravelTags(tagDTO);
      return ResponseEntity.ok(result);
  }

  @GetMapping("/option")
  public ResponseEntity<RequestData> getTravelOptions(@ModelAttribute TravelOptionDTO optionDTO) {
    RequestData result = adminTravelOptionService.getTravelOptions(optionDTO);
    return ResponseEntity.ok(result);
  }

    // 여행지 전체 목록 조회
    @GetMapping
    public ResponseEntity<RequestData> getTravelList(    
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "8") int size
      ) {
      log.info("여행지 페이지네이션 조회 요청 - page: {}, size: {}", page, size);
      return ResponseEntity.ok(adminTravelService.getTravelList(page, size));
    }

    // 여행지 상세 조회
    @GetMapping("/{travelNo}")
    public ResponseEntity<RequestData> getTravelDetail(@PathVariable Long travelNo) {
        log.info("여행지 상세 조회 요청 - travelNo: {}", travelNo);
        return ResponseEntity.ok(adminTravelService.getTravelDetail(travelNo));
    }

    // 테마 목록 조회
    @GetMapping("/thema")
    public ResponseEntity<RequestData> getTravelThemes(@ModelAttribute TravelThemaDTO themaDTO) {
      RequestData result = travelThemaService.getTravelThemes(themaDTO);
      return ResponseEntity.ok(result);
    }
}
