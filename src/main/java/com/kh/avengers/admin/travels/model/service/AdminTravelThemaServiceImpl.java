package com.kh.avengers.admin.travels.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.avengers.admin.travels.model.dao.TravelMapper;
import com.kh.avengers.admin.travels.model.dao.TravelThemaMapper;
import com.kh.avengers.admin.travels.model.dto.TravelThemaDTO;
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
public class AdminTravelThemaServiceImpl implements AdminTravelThemaService {
  
  private final TravelThemaMapper travelThemaMapper;
  private final TravelMapper travelMapper;
  private final ResponseUtil responseUtil;

  private void checkThemaExists(long themaNo) {
    if (travelThemaMapper.searchThemaNo(themaNo) <= 0) {
      throw new NotFoundException("Thema를 찾지 못했습니다.");
    }
  }
    
  @Override
  public RequestData getTravelThemes(TravelThemaDTO themaDTO) {
    List<TravelThemaDTO> themaList = travelThemaMapper.getThemaList(themaDTO);
    return responseUtil.rd("200", themaList, "Thema 목록 조회 완료");
  }
      
  @Override
  public RequestData getAdminThemes(TravelThemaDTO themaDTO) {
    List<TravelThemaDTO> themaList = travelThemaMapper.getAdminThemaList(themaDTO);
    return responseUtil.rd("200", themaList, "관리자 Thema 목록 조회 완료");
  }

  @Override
  public RequestData postAdminTheme(TravelThemaDTO themaDTO) {
        int result = travelThemaMapper.insertThema(themaDTO);
        if (result <= 0) {
            throw new InvalidException("Thema 등록 실패");
        }
        return responseUtil.rd("200", themaDTO, "Thema 등록 성공");
    }

    @Override
    public RequestData updateAdminTheme(TravelThemaDTO themaDTO) {
        checkThemaExists(themaDTO.getThemaNo());
        int result = travelThemaMapper.updateThema(themaDTO);
        if (result <= 0) {
            throw new UpdateException("Thema 수정 실패");
        }
        return responseUtil.rd("200", themaDTO, "Thema 수정 성공");
    }

    @Override
    public RequestData deleteAdminThemes(List<Long> themaNos) {
        int bridge = travelMapper.deleteTravelThemaBridgeByThemaNo(themaNos);
        int result = travelThemaMapper.deleteThemas(themaNos);
        if (result <= 0 & bridge <= 0) {
            throw new DeleteException("Thema 삭제 실패");
        }
        return responseUtil.rd("200", themaNos, "Thema 삭제 성공");
    }
}
