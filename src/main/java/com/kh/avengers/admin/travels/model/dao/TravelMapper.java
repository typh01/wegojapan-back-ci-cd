package com.kh.avengers.admin.travels.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.admin.travels.model.dto.TravelDTO;
import com.kh.avengers.admin.travels.model.dto.TravelImageDTO;
import com.kh.avengers.admin.travels.model.dto.TravelOptionBridgeDTO;
import com.kh.avengers.admin.travels.model.dto.TravelOptionDTO;
import com.kh.avengers.admin.travels.model.dto.TravelTagBridgeDTO;
import com.kh.avengers.admin.travels.model.dto.TravelTagDTO;
import com.kh.avengers.admin.travels.model.dto.TravelThemaBridgeDTO;
import com.kh.avengers.admin.travels.model.dto.TravelThemaDTO;
import com.kh.avengers.admin.travels.model.dto.TravelTimeDTO;


@Mapper
public interface TravelMapper {

    // 여행지 등록
    int insertTravel(TravelDTO travel);

    // 여행지 수정
    int updateTravel(TravelDTO travel);

    // 여행지 삭제 (소프트 딜리트)
    int deleteTravel(Long travelNo);

    // 여행지 상세 조회
    TravelDTO selectTravelByNo(Long travelNo);

    // 여행지 전체 목록 조회
    List<TravelDTO> selectTravelList();


    // 운영 시간 등록 / 삭제 / 조회
    int insertTravelTime(TravelTimeDTO time);
    int deleteTravelTimeByTravelNo(Long travelNo);
    List<TravelTimeDTO> selectTravelTimeList(Long travelNo);

    // 이미지 등록 / 삭제 / 조회
    int insertTravelImage(TravelImageDTO image);
    int deleteTravelImageByTravelNo(Long travelNo);
    List<TravelImageDTO> selectTravelImageList(Long travelNo);

    // 태그 브릿지 등록 / 삭제 / 조회
    int insertTravelTagBridge(TravelTagBridgeDTO tagBridge);
    int deleteTravelTagBridgeByTravelNo(Long travelNo);
    List<TravelTagDTO> selectTravelTagList(Long travelNo);

    // 옵션 브릿지 등록 / 삭제 / 조회
    int insertTravelOptionBridge(TravelOptionBridgeDTO optionBridge);
    int deleteTravelOptionBridgeByTravelNo(Long travelNo);
    List<TravelOptionDTO> selectTravelOptionList(Long travelNo);

    // 테마 브릿지 등록 / 삭제 / 조회
    int insertTravelThemaBridge(TravelThemaBridgeDTO themaBridge);
    int deleteTravelThemaBridgeByTravelNo(Long travelNo);
    List<TravelThemaDTO> selectTravelThemaList(Long travelNo);

    // 태그명으로 기존 조회
    Long selectTagByName(String tagName);

    // 태그 등록
    int insertTag(TravelTagDTO tagDTO);

    // 관리자 여행지 전체 목록 조회
    List<TravelDTO> selectAdminTravelList();

}
