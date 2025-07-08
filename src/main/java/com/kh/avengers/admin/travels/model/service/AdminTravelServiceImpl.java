    package com.kh.avengers.admin.travels.model.service;

    import java.util.HashMap;
    import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import com.kh.avengers.admin.travels.model.dao.TravelMapper;
    import com.kh.avengers.admin.travels.model.dto.*;
    import com.kh.avengers.common.dto.RequestData;
    import com.kh.avengers.exception.commonexception.DeleteException;
    import com.kh.avengers.exception.commonexception.InvalidException;
    import com.kh.avengers.exception.commonexception.NotFoundException;
    import com.kh.avengers.exception.commonexception.UpdateException;
import com.kh.avengers.reviews.model.dao.ReviewMapper;
import com.kh.avengers.util.ResponseUtil;

    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;

    @Slf4j
    @Service
    @RequiredArgsConstructor
    public class AdminTravelServiceImpl implements AdminTravelService {

        private final TravelMapper travelMapper;
        private final ResponseUtil responseUtil;
        private final ReviewMapper reviewMapper;

        private TravelDTO checkTravelExists(Long travelNo) {
            TravelDTO result = travelMapper.selectTravelByNo(travelNo);
            if (result == null) {
                throw new NotFoundException("해당 여행지를 찾을 수 없습니다.");
            }
            return result;
        }

        @Override
        public RequestData getTravelList(int page, int size) {
            int offset = (page - 1) * size;
            List<TravelDTO> travelList = travelMapper.selectPagedTravelList(offset, size);
            int total = travelMapper.selectTotalTravelCount();

            for (TravelDTO t : travelList) {
                List<TravelTimeDTO> timeList = travelMapper.selectTravelTimeList(t.getTravelNo());
                t.setTimeList(timeList);
                List<TravelImageDTO> imageList = travelMapper.selectTravelImageList(t.getTravelNo());
                t.setImageList(imageList);
                List<TravelTagDTO> tagList = travelMapper.selectTravelTagList(t.getTravelNo());
                t.setTagListForView(tagList);
                List<TravelThemaDTO> themaList = travelMapper.selectTravelThemaList(t.getTravelNo());
                t.setThemaListForView(themaList);
                List<TravelOptionDTO> optionList = travelMapper.selectTravelOptionList(t.getTravelNo());
                t.setOptionListForView(optionList);

                Double avgRating = reviewMapper.selectAverageRating(t.getTravelNo());
                t.setRating(avgRating != null ? avgRating : 0.0);
                System.out.println(avgRating);
            }

            Map<String, Object> result = new HashMap<>();
            result.put("data", travelList);
            result.put("total", total);

            return responseUtil.rd("200", result, "여행지 목록 조회 완료");
        }


        @Override
        public RequestData getTravelDetail(Long travelNo) {
            travelMapper.incrementViewCount(travelNo);
            TravelDTO travel = travelMapper.selectTravelByNo(travelNo);
            if (travel == null) throw new NotFoundException("해당 여행지를 찾을 수 없습니다.");


            travel.setTimeList(travelMapper.selectTravelTimeList(travelNo));
            travel.setImageList(travelMapper.selectTravelImageList(travelNo));
            travel.setTagListForView(travelMapper.selectTravelTagList(travelNo));
            travel.setOptionListForView(travelMapper.selectTravelOptionList(travelNo));
            travel.setThemaListForView(travelMapper.selectTravelThemaList(travelNo));
            travel.setRating(reviewMapper.selectAverageRating(travelNo));


            return responseUtil.rd("200", travel, "여행지 상세 조회 성공");
        }

        @Override
        @Transactional
        public RequestData postTravel(TravelDTO travelDTO) {
            int result = travelMapper.insertTravel(travelDTO);
            if (result <= 0) throw new InvalidException("여행지 등록 실패");

            Long travelNo = travelDTO.getTravelNo();

            if (travelDTO.getTimeList() != null) {
                for (TravelTimeDTO time : travelDTO.getTimeList()) {
                    time.setTravelNo(travelNo);
                    travelMapper.insertTravelTime(time);
                }
            }

            if (travelDTO.getImageList() != null) {
                for (TravelImageDTO image : travelDTO.getImageList()) {
                    image.setTravelNo(travelNo);
                    travelMapper.insertTravelImage(image);
                }
            }

            if (travelDTO.getTagList() != null) {
                for (TravelTagBridgeDTO tagBridge : travelDTO.getTagList()) {
                    tagBridge.setTravelNo(travelNo);

                    // tagNo가 없으면 tagName으로 태그 생성 후 tagNo 받아오기
                    
                    if (tagBridge.getTagNo() == null ) {
                        String tagName = tagBridge.getTagName();

                        // 기존 태그 먼저 조회
                        Long getTagNo = travelMapper.selectTagByName(tagName);
                        System.out.println("여기에요 여기 " + getTagNo);
                        if (getTagNo != null) {
                            // 이미 있으면 기존 tagNo 사용
                            tagBridge.setTagNo(getTagNo);
                        } else {
                            // 없으면 새로 생성
                            TravelTagDTO tagDTO = new TravelTagDTO();
                            tagDTO.setTagName(tagName);
                            travelMapper.insertTag(tagDTO);
                            tagDTO.setTagNo(travelMapper.selectTagByName(tagDTO.getTagName()));
                            tagBridge.setTagNo(tagDTO.getTagNo());
                        }
                    }

                        travelMapper.insertTravelTagBridge(tagBridge);
                }
            }

            if (travelDTO.getOptionList() != null) {
                for (TravelOptionBridgeDTO option : travelDTO.getOptionList()) {
                    option.setTravelNo(travelNo);
                    travelMapper.insertTravelOptionBridge(option);
                }
            }

            return responseUtil.rd("200", travelDTO, "여행지 등록 성공");
        }

        @Override
        @Transactional
        public RequestData updateTravel(TravelDTO travelDTO) {
            checkTravelExists(travelDTO.getTravelNo());

            // 1) 여행지 기본 정보 수정
            int result = travelMapper.updateTravel(travelDTO);
            if (result <= 0) throw new UpdateException("여행지 수정 실패");

            Long travelNo = travelDTO.getTravelNo();
            // 2) 기존 연관 데이터 삭제
            travelMapper.deleteTravelTimeByTravelNo(travelNo);
            travelMapper.deleteTravelImageByTravelNo(travelNo);
            travelMapper.deleteTravelTagBridgeByTravelNo(travelNo);
            travelMapper.deleteTravelOptionBridgeByTravelNo(travelNo);

            // 3) 시간 재등록
            if (travelDTO.getTimeList() != null) {
                for (TravelTimeDTO t : travelDTO.getTimeList()) {
                    t.setTravelNo(travelNo);
                    travelMapper.insertTravelTime(t);
                }
            }

            // 4) 이미지 재등록
            if (travelDTO.getImageList() != null) {
                for (TravelImageDTO img : travelDTO.getImageList()) {
                    img.setTravelNo(travelNo);
                    travelMapper.insertTravelImage(img);
                }
            }

            // 5) 태그 재등록 (postTravel과 동일하게 tagNo null 처리)
            if (travelDTO.getTagList() != null) {
                for (TravelTagBridgeDTO tb : travelDTO.getTagList()) {
                    tb.setTravelNo(travelNo);

                    if (tb.getTagNo() == null) {
                        String tagName = tb.getTagName();
                        Long existing = travelMapper.selectTagByName(tagName);
                        if (existing != null) {
                            tb.setTagNo(existing);
                        } else {
                            TravelTagDTO newTag = new TravelTagDTO();
                            newTag.setTagName(tagName);
                            travelMapper.insertTag(newTag);
                            // insertTag 호출 시 tagNo가 DTO에 셋팅되지 않으면 아래처럼 조회
                            tb.setTagNo(travelMapper.selectTagByName(tagName));
                        }
                    }

                    travelMapper.insertTravelTagBridge(tb);
                }
            }

            // 6) 옵션 재등록
            if (travelDTO.getOptionList() != null) {
                for (TravelOptionBridgeDTO ob : travelDTO.getOptionList()) {
                    ob.setTravelNo(travelNo);
                    travelMapper.insertTravelOptionBridge(ob);
                }
            }

            return responseUtil.rd("200", travelDTO, "여행지 수정 성공");
        }

        @Override
        public RequestData deleteTravel(Long travelNo, String status) {
            TravelDTO existing = checkTravelExists(travelNo);

            if (!status.equals("Y") && !status.equals("N")) {
                throw new InvalidException("잘못된 상태 값입니다. (Y/N만 허용)");
            }

            existing.setStatus(status);
            int result = travelMapper.deleteTravel(existing);
            if (result <= 0) throw new UpdateException("여행지 상태 변경 실패");

            return responseUtil.rd("200", travelNo, status.equals("Y") ? "활성화 성공" : "비활성화 성공");
        }

        @Override
        public RequestData getPagedTravelList(int page, int size) {
            int offset = (page - 1) * size;
            List<TravelDTO> list = travelMapper.selectPagedAdminTravelList(offset, size);
            long total = travelMapper.countAllTravels();
            PageResponse<TravelDTO> response = new PageResponse<>(list, page, size, total);
            return responseUtil.rd("200", response, "페이징 여행지 목록 조회 성공");
        }

        @Override
        public RequestData getFilteredTravelList(int page, int size, String search, String status, String period, String thema) {
            int offset = (page - 1) * size;

            Map<String, Object> filters = new HashMap<>();
            filters.put("search", search);
            filters.put("status", status);
            filters.put("period", period);
            filters.put("thema", thema);
            filters.put("offset", offset);
            filters.put("limit", size);

            List<TravelDTO> list = travelMapper.selectFilteredTravelList(filters);
            long total = travelMapper.countFilteredTravelList(filters);
            PageResponse<TravelDTO> response = new PageResponse<>(list, page, size, total);

            return responseUtil.rd("200", response, "조건 검색 완료");
        }


        @Override
        public RequestData getTravelThemas(Long travelNo) {
            List<TravelThemaDTO> themas = travelMapper.selectTravelThemaList(travelNo);
            return responseUtil.rd("200", themas, "여행지 테마 조회 성공");
        }

        @Override
        public RequestData addTravelThemaBridge(TravelThemaBridgeDTO dto) {
            int result = travelMapper.insertTravelThemaBridge(dto);
            if (result <= 0) throw new InvalidException("테마 추가 실패");
            return responseUtil.rd("200", dto, "테마 추가 성공");
        }

        @Override
        public RequestData deleteTravelThemaBridge(TravelThemaBridgeDTO dto) {
            int result = travelMapper.deleteTravelThemaBridgeByTravelNoAndThemaNo(dto);
            if (result <= 0) throw new DeleteException("테마 삭제 실패");
            return responseUtil.rd("200", dto, "테마 삭제 성공");
        }


        // 특정 구에 속한 여행지 목록 조회
        @Override
        public RequestData getTravelPlacesByGu(String guName) {
            log.info("특정 구의 여행지 조회 서비스 시작 >> 구명: {}", guName);

            // 구명으로 여행지 목록 조회
            List<TravelDTO> travelList = travelMapper.selectTravelListByGuName(guName);

            log.info("구별 여행지 조회 완료 >> 구명: {}, 여행지 개수: {}", guName, travelList.size());

            return responseUtil.rd("200", travelList, guName + " 지역의 여행지 목록 조회 성공");
        }
        
    }
