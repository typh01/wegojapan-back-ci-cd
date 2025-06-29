package com.kh.avengers.admin.travels.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.avengers.admin.travels.model.dao.TravelCityMapper;
import com.kh.avengers.admin.travels.model.dto.TravelCityDTO;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.DeleteException;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.exception.commonexception.UpdateException;
import com.kh.avengers.exception.util.guNotFoundException;
import com.kh.avengers.util.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminTravelCityServiceImpl implements AdminTravelCityService {
    private final TravelCityMapper travelCityMapper;
    private final ResponseUtil responseUtil;

  private void checkCityExists(long cityNo) {
    if (travelCityMapper.searchCityNo(cityNo) <= 0) {
        throw new guNotFoundException("City를 찾지 못했습니다.");
    }
  }

  @Override
  public RequestData getCities(TravelCityDTO cityDTO) {
    List<TravelCityDTO> cityList = travelCityMapper.selectCityList(cityDTO);
    return responseUtil.rd("200", cityList, "City 목록 조회 완료");
  }

  @Override
  public RequestData getAdminCities(TravelCityDTO cityDTO) {
    List<TravelCityDTO> cityList = travelCityMapper.selectCity(cityDTO);
    return responseUtil.rd("200", cityList, "관리자 City 목록 조회 완료");
  }
  
  @Override
  public RequestData postCity(TravelCityDTO cityDTO) {
      int result = travelCityMapper.insertCity(cityDTO);
      if(result <= 0){
        throw new  InvalidException("City 등록 실패");
      }
    return responseUtil.rd("200", cityDTO, "City 등록 성공");
  }

  @Override
  public RequestData updateCity(TravelCityDTO cityDTO) {
    checkCityExists(cityDTO.getCityNo());
    int result = travelCityMapper.updateCity(cityDTO);
        if(result <= 0){
          throw new UpdateException("City 수정 실패");
    }
    return responseUtil.rd("200", cityDTO, "City 수정 성공");
  }

  @Override
  public RequestData deleteCity(Long cityNo) {
    checkCityExists(cityNo);
        int result = travelCityMapper.deleteCity(cityNo);
        if(result <= 0){
          throw new DeleteException("City 삭제 실패");
    }
    return responseUtil.rd("200", cityNo, "City 삭제 성공");
  }


}
