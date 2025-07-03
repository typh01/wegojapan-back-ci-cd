package com.kh.avengers.admin.travels.model.service;

import com.kh.avengers.admin.travels.model.dto.*;
import com.kh.avengers.common.dto.RequestData;

public interface AdminTravelService {

    // 페이징 처리된 여행지 목록 조회
    RequestData getTravelList();

    RequestData getFilteredTravelList(int page, int size, String search, String status, String period, String thema);

    RequestData getPagedTravelList(int page, int size);

    RequestData getTravelThemas(Long travelNo);

    RequestData addTravelThemaBridge(TravelThemaBridgeDTO dto);

    RequestData deleteTravelThemaBridge(TravelThemaBridgeDTO dto);

    // 여행지 상세 조회
    RequestData getTravelDetail(Long travelNo);

    // 여행지 등록
    RequestData postTravel(TravelDTO travelDTO);

    // 여행지 수정
    RequestData updateTravel(TravelDTO travelDTO);

    // 여행지 삭제 (소프트 딜리트)
    RequestData deleteTravel(Long travelNo, String status);
    
    // 특정 구에 속한 여행지 목록 조회
    RequestData getTravelPlacesByGu(String guName);
}
