package com.kh.avengers.admin.travels.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.avengers.admin.travels.model.dto.TravelDTO;
import com.kh.avengers.admin.travels.model.dto.TravelImageDTO;
import com.kh.avengers.admin.travels.model.dto.TravelOptionBridgeDTO;
import com.kh.avengers.admin.travels.model.dto.TravelOptionDTO;
import com.kh.avengers.admin.travels.model.dto.TravelTagBridgeDTO;
import com.kh.avengers.admin.travels.model.dto.TravelTagDTO;
import com.kh.avengers.admin.travels.model.dto.TravelThemaBridgeDTO;
import com.kh.avengers.admin.travels.model.dto.TravelThemaDTO;
import com.kh.avengers.admin.travels.model.dto.TravelTimeDTO;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface TravelMapper {

    // 여행지 등록
    int insertTravel(TravelDTO travel);

    // 여행지 수정
    int updateTravel(TravelDTO travel);

    // 여행지 삭제 (소프트 딜리트)
    int deleteTravel(TravelDTO travel);

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
    int deleteTravelTagBridgeByTagNo(List<Long> tagNos);
    
    // 옵션 브릿지 등록 / 삭제 / 조회
    int insertTravelOptionBridge(TravelOptionBridgeDTO optionBridge);
    int deleteTravelOptionBridgeByTravelNo(Long travelNo);
    List<TravelOptionDTO> selectTravelOptionList(Long travelNo);
    int deleteTravelOptionBridgeByOptionNo(List<Long> optionNos);
    
    // 테마 브릿지 등록 / 삭제 / 조회
    int insertTravelThemaBridge(TravelThemaBridgeDTO themaBridge);
    int deleteTravelThemaBridgeByTravelNo(Long travelNo);
    List<TravelThemaDTO> selectTravelThemaList(Long travelNo);
    int deleteTravelThemaBridgeByThemaNo(List<Long> themaNos);

    // 태그명으로 기존 조회
    Long selectTagByName(String tagName);

    // 태그 등록
    int insertTag(TravelTagDTO tagDTO);

    // 관리자 여행지 전체 목록 조회
    List<TravelDTO> selectAdminTravelList();
    
    // 특정 구에 속한 여행지 목록 조회
    List<TravelDTO> selectTravelListByGuName(@Param("guName") String guName);

    List<TravelDTO> selectPagedAdminTravelList(@Param("offset") int offset, @Param("limit") int limit);
    long countAllTravels();
    int deleteTravelThemaBridgeByTravelNoAndThemaNo(TravelThemaBridgeDTO dto);

    List<TravelDTO> selectFilteredTravelList(Map<String, Object> filters);
    
    long countFilteredTravelList(Map<String, Object> filters);

    // 즐겨찾기 중복 확인
    Long checkedBook(Map<String, String> book);

    // 즐겨찾기 올리기
    Long insertBookCount(Map<String, String> book);
  
    // 즐겨찾기 취소하기
    Long deleteBookCount(Map<String, String> book);

<<<<<<< HEAD
    List<TravelDTO> selectBookList(Long memberNo);
=======
    int incrementViewCount(Long travelNo);

    List<TravelDTO> selectPagedTravelList(@Param("offset") int offset, @Param("limit") int limit);
    int selectTotalTravelCount();
>>>>>>> d1560804b674571ca8864ebb7ea59a46b7a18802

}

    
