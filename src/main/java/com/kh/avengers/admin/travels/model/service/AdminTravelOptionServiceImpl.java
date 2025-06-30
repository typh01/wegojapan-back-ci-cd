package com.kh.avengers.admin.travels.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.avengers.admin.travels.model.dao.TravelOptionMapper;
import com.kh.avengers.admin.travels.model.dto.TravelOptionDTO;
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
public class AdminTravelOptionServiceImpl implements AdminTravelOptionService {

    private final TravelOptionMapper travelOptionMapper;
    private final ResponseUtil responseUtil;
    
    private void checkOptionExists(long optionNo) {
      if (travelOptionMapper.searchOptionNo(optionNo) <= 0) {
        throw new NotFoundException("Option을 찾지 못했습니다.");
      }
    }
    
    @Override
    public RequestData getTravelOptions(TravelOptionDTO optionDTO) {
        List<TravelOptionDTO> optionList = travelOptionMapper.getAdminOptionList(optionDTO);
        return responseUtil.rd("200", optionList, "Option 목록 조회 완료");
    }

    @Override
    public RequestData getAdminOptions(TravelOptionDTO optionDTO) {
        List<TravelOptionDTO> optionList = travelOptionMapper.getAdminOptionList(optionDTO);
        return responseUtil.rd("200", optionList, "관리자 Option 목록 조회 완료");
    }

    @Override
    public RequestData postAdminOption(TravelOptionDTO optionDTO) {
        int result = travelOptionMapper.insertOption(optionDTO);
        if (result <= 0) {
            throw new InvalidException("Option 등록 실패");
        }
        return responseUtil.rd("200", optionDTO, "Option 등록 성공");
    }

    @Override
    public RequestData updateAdminOption(TravelOptionDTO optionDTO) {
        checkOptionExists(optionDTO.getOptionNo());
        int result = travelOptionMapper.updateOption(optionDTO);
        if (result <= 0) {
            throw new UpdateException("Option 수정 실패");
        }
        return responseUtil.rd("200", optionDTO, "Option 수정 성공");
    }

    @Override
    public RequestData deleteAdminOptions(List<Long> optionNos) {
        int result = travelOptionMapper.deleteOptions(optionNos);
        if (result <= 0) {
            throw new DeleteException("Option 삭제 실패");
        }
        return responseUtil.rd("200", optionNos, "Option 삭제 성공");
    }

}
