package com.kh.avengers.admin.travels.model.service;

import com.kh.avengers.admin.travels.model.dto.*;
import com.kh.avengers.common.dto.RequestData;

public interface AdminTravelService {

    // 여행지 전체 목록 조회
    RequestData getTravelList();

    // 여행지 상세 조회
    RequestData getTravelDetail(Long travelNo);

    // 여행지 등록
    RequestData postTravel(TravelDTO travelDTO);

    // 여행지 수정
    RequestData updateTravel(TravelDTO travelDTO);

    // 여행지 삭제 (소프트 딜리트)
    RequestData deleteTravel(Long travelNo);

    // 특정 구에 속한 여행지 목록 조회
    RequestData getTravelPlacesByGu(String guName);
}
