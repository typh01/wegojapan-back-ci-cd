package com.kh.avengers.travels.model.service;

import com.kh.avengers.common.dto.RequestData;

public interface UserTravelService {
    RequestData getTravelCategories();
    RequestData getTravelGus();
    RequestData getTravelTags();
    RequestData getTravelOptions();
    RequestData getTravelList(int page, int size);
    RequestData getTravelDetail(Long travelNo);
    RequestData searchTravels(int page, int size, String search, String category, String district, String tags, String facilities, String thema);
}
