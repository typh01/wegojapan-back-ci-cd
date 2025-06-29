package com.kh.avengers.admin.travels.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.admin.travels.model.dto.TravelCategoryDTO;
import com.kh.avengers.admin.travels.model.service.AdminTravelCategoryService;
import com.kh.avengers.common.dto.RequestData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin/travel/category")
@RequiredArgsConstructor
public class AdminTravelCategoryController {
  private final AdminTravelCategoryService travelCategoryService;

  @GetMapping
  public ResponseEntity<RequestData> getTravelCategories(@ModelAttribute TravelCategoryDTO categoryDTO) {
    RequestData result = travelCategoryService.getAdminCategories(categoryDTO);
      return ResponseEntity.ok(result);
  }

  @PostMapping
  public ResponseEntity<RequestData> postTravelCategories(@RequestBody TravelCategoryDTO categoryDTO) {
    RequestData result = travelCategoryService.postAdminCategories(categoryDTO);
      return ResponseEntity.ok(result);
  }

  @PutMapping("/{categoryNo}")
  public ResponseEntity<RequestData> updateTravelCategories(
    @PathVariable long categoryNo,
    @RequestBody TravelCategoryDTO categoryDTO) {
      categoryDTO.setCategoryNo(categoryNo);
    RequestData result = travelCategoryService.updateAdminCategory(categoryDTO);
      return ResponseEntity.ok(result);
  }

  @DeleteMapping
  public ResponseEntity<RequestData> deleteTravelCategories(@RequestBody List<Long> categoryNos) {
    RequestData result = travelCategoryService.deleteAdminCategories(categoryNos);
      return ResponseEntity.ok(result);
  }
}
