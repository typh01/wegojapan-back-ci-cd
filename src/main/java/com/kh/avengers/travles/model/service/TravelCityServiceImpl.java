package com.kh.avengers.travles.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.DeleteException;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.exception.commonexception.UpdateException;
import com.kh.avengers.exception.util.guNotFoundException;
import com.kh.avengers.travles.model.dao.TravelCityMapper;
import com.kh.avengers.travles.model.dto.TravelCityDTO;
import com.kh.avengers.util.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelCityServiceImpl implements TravelCityService {
    private final TravelCityMapper travelCityMapper;
    private final ResponseUtil responseUtil;

    private void checkCityExists(long cityNo) {
    if (travelCityMapper.searchCityNo(cityNo) <= 0) {
        throw new guNotFoundException("City를 찾지 못했습니다.");
    }
  }

  @Override
  public RequestData getTravelCities(TravelCityDTO cityDTO) {
    List<TravelCityDTO> cityList = travelCityMapper.selectTravelCityList(cityDTO);
    return responseUtil.rd("200", cityList, "City 목록 조회 완료");
  }

  @Override
  public RequestData postTravelCity(TravelCityDTO cityDTO) {
      int result = travelCityMapper.insertTravelCity(cityDTO);
      if(result <= 0){
        throw new  InvalidException("City 등록 실패");
      }
    return responseUtil.rd("200", cityDTO, "City 등록 성공");
  }

  @Override
  public RequestData updateTravelCity(TravelCityDTO cityDTO) {
    checkCityExists(cityDTO.getCityNo());
    int result = travelCityMapper.updateTravelCity(cityDTO);
        if(result <= 0){
          throw new UpdateException("City 수정 실패");
    }
    return responseUtil.rd("200", cityDTO, "City 수정 성공");
  }

  @Override
  public RequestData deleteTravelCity(TravelCityDTO cityDTO) {
    checkCityExists(cityDTO.getCityNo());
        int result = travelCityMapper.deleteTravelCity(cityDTO.getCityNo());
        if(result <= 0){
          throw new DeleteException("GU 삭제 실패");
    }
    return responseUtil.rd("200", cityDTO, "GU 삭제 성공");
  }

}
