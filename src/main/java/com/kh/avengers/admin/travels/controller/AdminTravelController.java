package com.kh.avengers.admin.travels.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kh.avengers.admin.travels.model.dto.TravelDTO;
import com.kh.avengers.admin.travels.model.dto.TravelThemaBridgeDTO;
import com.kh.avengers.admin.travels.model.service.AdminTravelService;
import com.kh.avengers.common.dto.RequestData;

@Slf4j
@RestController
@RequestMapping("/api/admin/travels")
@RequiredArgsConstructor
public class AdminTravelController {

    private final AdminTravelService adminTravelService;

    @GetMapping("/list")
    public ResponseEntity<RequestData> getPagedTravelList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        RequestData result = adminTravelService.getPagedTravelList(page, size);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/filter/search")
    public ResponseEntity<RequestData> searchTravelList(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String period,
            @RequestParam(required = false) String thema
    ) {
        RequestData result = adminTravelService.getFilteredTravelList(page, size, search, status, period, thema);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{travelNo}/themas")
    public ResponseEntity<RequestData> getTravelThemas(@PathVariable Long travelNo) {
        RequestData result = adminTravelService.getTravelThemas(travelNo);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/thema-bridge")
    public ResponseEntity<RequestData> addTravelThema(@RequestBody TravelThemaBridgeDTO dto) {
        RequestData result = adminTravelService.addTravelThemaBridge(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/thema-bridge")
    public ResponseEntity<RequestData> deleteTravelThema(@RequestBody TravelThemaBridgeDTO dto) {
        RequestData result = adminTravelService.deleteTravelThemaBridge(dto);
        return ResponseEntity.ok(result);
    }

    
    @GetMapping("/{travelNo}")
    public ResponseEntity<RequestData> getTravelDetail(@PathVariable Long travelNo) {
        RequestData result = adminTravelService.getTravelDetail(travelNo);
        return ResponseEntity.ok(result);
    }
    
    // 여행지 등록
    @PostMapping
    public ResponseEntity<RequestData> postTravel(@Valid @RequestBody TravelDTO travelDTO) {
        RequestData result = adminTravelService.postTravel(travelDTO);
        return ResponseEntity.ok(result);
    }

    // 여행지 수정
    @PutMapping("/{travelNo}")
    public ResponseEntity<RequestData> updateTravel(@PathVariable Long travelNo,
                                                    @Valid @RequestBody TravelDTO travelDTO) {
        travelDTO.setTravelNo(travelNo);
        RequestData result = adminTravelService.updateTravel(travelDTO);
        return ResponseEntity.ok(result);
    }

    // 여행지 삭제 (소프트 딜리트)
    @DeleteMapping("/{travelNo}")
    public ResponseEntity<RequestData> deleteTravel(@PathVariable Long travelNo,
                                                    @RequestBody Map<String, String> statusMap) {
        String status = statusMap.get("status");
        RequestData result = adminTravelService.deleteTravel(travelNo, status);
        return ResponseEntity.ok(result);
    }

    
}
