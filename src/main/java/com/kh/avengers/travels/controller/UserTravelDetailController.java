package com.kh.avengers.travels.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.travels.model.service.UserTravelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/travels/user")
@RequiredArgsConstructor
public class UserTravelDetailController {
   private final UserTravelService userTravelService;
    
    @GetMapping("/search")
    public ResponseEntity<RequestData> searchTravels(
        @RequestParam(value = "page") int page,
        @RequestParam(value = "size") int size,
        @RequestParam(value = "search", required = false) String search,
        @RequestParam(value = "category", required = false) String category,
        @RequestParam(value = "district", required = false) String district,
        @RequestParam(value = "tags", required = false) String tags,
        @RequestParam(value = "facilities", required = false) String facilities,
        @RequestParam(value = "thema", required = false) String thema
    ) {
        return ResponseEntity.ok(userTravelService.searchTravels(page, size, search, category, district, tags, facilities, thema));
    }

    @GetMapping("/tags")
    public ResponseEntity<RequestData> getUserTravelTags() {
        RequestData result = userTravelService.getTravelTags();
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/option")
    public ResponseEntity<RequestData> getUserTravelOptions() {
        RequestData result = userTravelService.getTravelOptions();
        return ResponseEntity.ok(result);
    }
}
