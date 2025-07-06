    package com.kh.avengers.travels.model.service;

    import com.kh.avengers.admin.travels.model.dao.TravelMapper;
    import com.kh.avengers.admin.travels.model.dto.TravelDTO;
    import com.kh.avengers.common.dto.RequestData;
    import com.kh.avengers.travels.model.dao.UserTravelMapper;
    import com.kh.avengers.travels.model.dto.UserTravelDTO;
    import com.kh.avengers.util.ResponseUtil;

    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    @Slf4j
    @Service
    @RequiredArgsConstructor
    public class UserTravelServiceImpl implements UserTravelService {
        private final UserTravelMapper userTravelMapper;
        private final TravelMapper travelMapper;
        private final ResponseUtil responseUtil;

        @Override
        public RequestData getTravelCategories() {
            return responseUtil.rd("200", userTravelMapper.getCategoryList(), "카테고리 목록 조회 성공");
        }

        @Override
        public RequestData getTravelGus() {
            return responseUtil.rd("200", userTravelMapper.getGuList(), "지역 목록 조회 성공");
        }

        @Override
        public RequestData getTravelTags() {
            return responseUtil.rd("200", userTravelMapper.getTagList(), "태그 목록 조회 성공");
        }

        @Override
        public RequestData getTravelOptions() {
            return responseUtil.rd("200", userTravelMapper.getOptionList(), "편의시설 목록 조회 성공");
        }

        @Override
        public RequestData getTravelList(int page, int size) {
            int offset = (page - 1) * size;
            List<UserTravelDTO> list = userTravelMapper.selectPagedTravelList(offset, size);
            int total = userTravelMapper.selectTotalTravelCount();

            Map<String, Object> res = new HashMap<>();
            res.put("data", list);
            res.put("total", total);

            return responseUtil.rd("200", res, "기본 여행지 목록 조회 성공");
        }

        @Override
        public RequestData getTravelDetail(Long travelNo) {
            UserTravelDTO dto = userTravelMapper.selectTravelDetail(travelNo);
            return responseUtil.rd("200", dto, "여행지 상세 조회 성공");
        }

        @Override
        public RequestData searchTravels(int page, int size, String search, String category, String district, String tags, String facilities, String thema) {
            Map<String, Object> filters = new HashMap<>();
                filters.put("search", search);
                filters.put("category", category);
                filters.put("district", district);
                filters.put("tags", tags);
                filters.put("facilities", facilities);
                filters.put("thema", thema);
                filters.put("offset", (page - 1) * size);
                filters.put("limit", size);

                if (tags != null && !tags.isBlank()) {
                    filters.put("tagsList", List.of(tags.split(",")));
                }

                if (facilities != null && !facilities.isBlank()) {
                    filters.put("facilitiesList", List.of(facilities.split(",")));
                }

                if (thema != null && !thema.isBlank()) {
                    filters.put("themasList", List.of(thema.split(",")));
                }

                List<UserTravelDTO> userList = userTravelMapper.selectFilteredTravelList(filters);
                long total = userTravelMapper.countFilteredTravelList(filters);
                List<TravelDTO> travelList = new ArrayList<>();

                for (UserTravelDTO u : userList) {
                    TravelDTO t = new TravelDTO();
                    
                    t.setTravelNo(u.getTravelNo());
                    t.setTitle(u.getTitle());
                    t.setAddress(u.getAddress());
                    t.setExplain(u.getExplain());
                    t.setMapX(u.getMapX());
                    t.setMapY(u.getMapY());
                    t.setTel(u.getTel());
                    t.setEmail(u.getEmail());
                    t.setWebsite(u.getWebsite());
                    t.setViewCount(u.getViewCount());
                    t.setCategoryName(u.getCategoryName());
                    t.setGuName(u.getGuName());

                    Long travelNo = u.getTravelNo();
                    t.setImageList(travelMapper.selectTravelImageList(travelNo));
                    t.setTagListForView(travelMapper.selectTravelTagList(travelNo));
                    t.setOptionListForView(travelMapper.selectTravelOptionList(travelNo));
                    t.setTimeList(travelMapper.selectTravelTimeList(travelNo));

                    Double avgRating = travelMapper.getAverageRatingByTravelNo(travelNo);
                    t.setRating(avgRating != null ? avgRating : 0.0);

                    travelList.add(t);
                }


            Map<String, Object> result = new HashMap<>();
            result.put("content", travelList);
            result.put("totalPages", (int) Math.ceil((double) total / size));

            return responseUtil.rd("200", result, "여행지 필터 조회 성공");
        }

    }
