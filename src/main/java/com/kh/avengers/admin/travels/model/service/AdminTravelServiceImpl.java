    package com.kh.avengers.admin.travels.model.service;

    import java.util.List;

    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import com.kh.avengers.admin.travels.model.dao.TravelMapper;
    import com.kh.avengers.admin.travels.model.dto.*;
    import com.kh.avengers.common.dto.RequestData;
    import com.kh.avengers.exception.commonexception.DeleteException;
    import com.kh.avengers.exception.commonexception.InvalidException;
    import com.kh.avengers.exception.commonexception.NotFoundException;
    import com.kh.avengers.exception.commonexception.UpdateException;
    import com.kh.avengers.util.ResponseUtil;

    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;

    @Slf4j
    @Service
    @RequiredArgsConstructor
    public class AdminTravelServiceImpl implements AdminTravelService {

        private final TravelMapper travelMapper;
        private final ResponseUtil responseUtil;

        private void checkTravelExists(Long travelNo) {
            if (travelMapper.selectTravelByNo(travelNo) == null) {
                throw new NotFoundException("해당 여행지를 찾을 수 없습니다.");
            }
        }

        @Override
        public RequestData getTravelList() {
            List<TravelDTO> travelList = travelMapper.selectAdminTravelList();

        for (TravelDTO t : travelList) {
            List<TravelTagDTO> tagList = travelMapper.selectTravelTagList(t.getTravelNo());
            t.setTagListForView(tagList);
            List<TravelThemaDTO> themaList = travelMapper.selectTravelThemaList(t.getTravelNo());
            t.setThemaListForView(themaList);
            List<TravelOptionDTO> optionList = travelMapper.selectTravelOptionList(t.getTravelNo());
            t.setOptionListForView(optionList);
        }

            return responseUtil.rd("200", travelList, "여행지 목록 조회 완료");
        }

        @Override
        public RequestData getTravelDetail(Long travelNo) {
            TravelDTO travel = travelMapper.selectTravelByNo(travelNo);
            if (travel == null) throw new NotFoundException("해당 여행지를 찾을 수 없습니다.");

            travel.setTimeList(travelMapper.selectTravelTimeList(travelNo));
            travel.setImageList(travelMapper.selectTravelImageList(travelNo));
            travel.setTagListForView(travelMapper.selectTravelTagList(travelNo));
            travel.setOptionListForView(travelMapper.selectTravelOptionList(travelNo));
            travel.setThemaListForView(travelMapper.selectTravelThemaList(travelNo));


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
                        System.out.println("ㅣ여기에요 여기 " + getTagNo);
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

            int result = travelMapper.updateTravel(travelDTO);
            if (result <= 0) throw new UpdateException("여행지 수정 실패");

            Long travelNo = travelDTO.getTravelNo();
            travelMapper.deleteTravelTimeByTravelNo(travelNo);
            travelMapper.deleteTravelImageByTravelNo(travelNo);
            travelMapper.deleteTravelTagBridgeByTravelNo(travelNo);
            travelMapper.deleteTravelOptionBridgeByTravelNo(travelNo);

            postTravel(travelDTO); // insert로 재등록

            return responseUtil.rd("200", travelDTO, "여행지 수정 성공");
        }

        @Override
        public RequestData deleteTravel(Long travelNo) {
            checkTravelExists(travelNo);

            int result = travelMapper.deleteTravel(travelNo);
            if (result <= 0) throw new DeleteException("여행지 삭제 실패");

            return responseUtil.rd("200", travelNo, "여행지 삭제 성공");
        }
    }
