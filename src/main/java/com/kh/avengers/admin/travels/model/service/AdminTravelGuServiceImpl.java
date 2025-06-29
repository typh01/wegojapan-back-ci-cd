package com.kh.avengers.admin.travels.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.avengers.admin.travels.model.dao.TravelGuMapper;
import com.kh.avengers.admin.travels.model.dto.TravelGuDTO;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.DeleteException;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.exception.commonexception.UpdateException;
import com.kh.avengers.exception.util.guNotExistException;
import com.kh.avengers.exception.util.guNotFoundException;
import com.kh.avengers.util.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminTravelGuServiceImpl implements AdminTravelGuService {
    private final TravelGuMapper travelGuMapper;
    private final ResponseUtil responseUtil;

    private void checkGuExists(long guNo) {
    if (travelGuMapper.searchGuNo(guNo) <= 0) {
        throw new guNotFoundException("GU를 찾지 못했습니다.");
    }
  }

    @Override
    public RequestData getTravelGus(TravelGuDTO guDTO) {
        List<TravelGuDTO> guList = travelGuMapper.getGuList(guDTO);
        if(guList == null || guList.isEmpty()){
          throw new guNotExistException("GU가 존재하지 않습니다.");
        }
        return responseUtil.rd("200", guList, "GU 목록 조회 완료");
    }

    @Override
    public RequestData getAdminGus(TravelGuDTO guDTO) {
        List<TravelGuDTO> guList = travelGuMapper.getAdminGu(guDTO);
        return responseUtil.rd("200", guList, "GU 목록 조회 완료");
    }

    @Override
    public RequestData postAdminGus(TravelGuDTO guDTO) {
        int result = travelGuMapper.insertGu(guDTO);
        if(result <= 0){
          throw new  InvalidException("GU 등록 실패");
        }
        return responseUtil.rd("200", guDTO, "GU 등록 성공");
      }
      
      @Override
      public RequestData updateAdminGus(TravelGuDTO guDTO) {
        checkGuExists(guDTO.getGuNo());
        int result = travelGuMapper.updateGu(guDTO);
        if(result <= 0){
          throw new UpdateException("GU 수정 실패");
        }
        return responseUtil.rd("200", guDTO, "GU 수정 성공");
      }
      
      @Override
      public RequestData deleteAdminGus(Long guNo) {
        checkGuExists(guNo);
        int result = travelGuMapper.deleteGu(guNo);
        if(result <= 0){
          throw new DeleteException("GU 삭제 실패");
        }
        return responseUtil.rd("200", result, "GU 삭제 성공");
    }
}
