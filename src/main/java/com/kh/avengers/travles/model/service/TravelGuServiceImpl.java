package com.kh.avengers.travles.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.DeleteException;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.exception.commonexception.UpdateException;
import com.kh.avengers.exception.util.guNotExistException;
import com.kh.avengers.exception.util.guNotFoundException;
import com.kh.avengers.travles.model.dao.TravelGuMapper;
import com.kh.avengers.travles.model.dto.TravelGuDTO;
import com.kh.avengers.util.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelGuServiceImpl implements TravelGuService {
    private final TravelGuMapper travelGuMapper;
    private final ResponseUtil responseUtil;

    private void checkGuExists(long guNo) {
    if (travelGuMapper.searchGuNo(guNo) <= 0) {
        throw new guNotFoundException("GU를 찾지 못했습니다.");
    }
  }

    @Override
    public RequestData getTravelGus(TravelGuDTO guDTO) {
        List<TravelGuDTO> guList = travelGuMapper.selectTravelGuList(guDTO);
        if(guList == null || guList.isEmpty()){
          throw new guNotExistException("GU가 존재하지 않습니다.");
        }
        return responseUtil.rd("200", guList, "GU 목록 조회 완료");
    }

    @Override
    public RequestData postTravelGus(TravelGuDTO guDTO) {
        int result = travelGuMapper.insertTravelGus(guDTO);
        if(result <= 0){
          throw new  InvalidException("GU 등록 실패");
        }
        return responseUtil.rd("200", guDTO, "GU 등록 성공");
      }
      
      @Override
      public RequestData updateTravelGus(TravelGuDTO guDTO) {
        checkGuExists(guDTO.getGuNo());
        int result = travelGuMapper.updateTravelGus(guDTO);
        if(result <= 0){
          throw new UpdateException("GU 수정 실패");
        }
        return responseUtil.rd("200", guDTO, "GU 수정 성공");
      }
      
      @Override
      public RequestData deleteTravelGus(TravelGuDTO guDTO) {
        checkGuExists(guDTO.getGuNo());
        int result = travelGuMapper.deleteTravelGus(guDTO.getGuNo());
        if(result <= 0){
          throw new DeleteException("GU 삭제 실패");
        }
        return responseUtil.rd("200", guDTO, "GU 삭제 성공");
    }
}
