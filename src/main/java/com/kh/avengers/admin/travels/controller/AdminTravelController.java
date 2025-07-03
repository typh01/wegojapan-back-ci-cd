package com.kh.avengers.admin.travels.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kh.avengers.admin.travels.model.dto.TravelDTO;
import com.kh.avengers.admin.travels.model.service.AdminTravelService;
import com.kh.avengers.common.dto.RequestData;

@Slf4j
@RestController
@RequestMapping("/api/admin/travels")
@RequiredArgsConstructor
public class AdminTravelController {

    private final AdminTravelService adminTravelService;

    // 여행지 전체 목록 조회
    @GetMapping
    public ResponseEntity<RequestData> getTravelList() {
        log.info("[ADMIN] 여행지 전체 목록 조회 요청");
        return ResponseEntity.ok(adminTravelService.getTravelList());
    }

    // 여행지 등록
    @PostMapping
    public ResponseEntity<RequestData> postTravel(@Valid @RequestBody TravelDTO travelDTO) {
        log.info("[ADMIN] 여행지 등록 요청: {}", travelDTO);
        return ResponseEntity.ok(adminTravelService.postTravel(travelDTO));
    }

    // 여행지 수정
    @PutMapping("/{travelNo}")
    public ResponseEntity<RequestData> updateTravel(@PathVariable Long travelNo,
                                                    @Valid @RequestBody TravelDTO travelDTO) {
        travelDTO.setTravelNo(travelNo);
        log.info("[ADMIN] 여행지 수정 요청 - travelNo: {}", travelNo);
        return ResponseEntity.ok(adminTravelService.updateTravel(travelDTO));
    }

    // 여행지 삭제 (소프트 딜리트)
    @DeleteMapping("/{travelNo}")
    public ResponseEntity<RequestData> deleteTravel(@PathVariable Long travelNo) {
        log.info("[ADMIN] 여행지 삭제 요청 - travelNo: {}", travelNo);
        return ResponseEntity.ok(adminTravelService.deleteTravel(travelNo));
    }

    // 특정 구에 속한 여행지 목록 조회
    @GetMapping("/places")
    public ResponseEntity<RequestData> getTravelPlacesByGu(@RequestParam("guName") String guName) {
        log.info("특정 구의 여행지 조회 요청 >> 구명: {}", guName);
        RequestData result = adminTravelService.getTravelPlacesByGu(guName);
        return ResponseEntity.ok(result);
    }
}
