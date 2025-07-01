package com.kh.avengers.admin.travels.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.avengers.admin.travels.model.dao.TravelTagMapper;
import com.kh.avengers.admin.travels.model.dto.TravelTagDTO;
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
public class AdminTravelTagServiceImpl implements AdminTravelTagService {

    private final TravelTagMapper travelTagMapper;
    private final ResponseUtil responseUtil;

    private void checkTagExists(long tagNo) {
        if (travelTagMapper.searchTagNo(tagNo) <= 0) {
            throw new NotFoundException("Tag를 찾지 못했습니다.");
        }
    }

    @Override
    public RequestData getTravelTags(TravelTagDTO tagDTO) {
        List<TravelTagDTO> tagList = travelTagMapper.getTagList(tagDTO);
        return responseUtil.rd("200", tagList, "Tag 목록 조회 완료");
    }

    @Override
    public RequestData getAdminTags(TravelTagDTO tagDTO) {
        List<TravelTagDTO> tagList = travelTagMapper.getAdminTagList(tagDTO);
        return responseUtil.rd("200", tagList, "관리자 Tag 목록 조회 완료");
    }

    @Override
    public RequestData postAdminTag(TravelTagDTO tagDTO) {
        int result = travelTagMapper.insertTag(tagDTO);
        if (result <= 0) {
            throw new InvalidException("Tag 등록 실패");
        }
        return responseUtil.rd("200", tagDTO, "Tag 등록 성공");
    }

    @Override
    public RequestData updateAdminTag(TravelTagDTO tagDTO) {
        checkTagExists(tagDTO.getTagNo());
        int result = travelTagMapper.updateTag(tagDTO);
        if (result <= 0) {
            throw new UpdateException("Tag 수정 실패");
        }
        return responseUtil.rd("200", tagDTO, "Tag 수정 성공");
    }

    @Override
    public RequestData deleteAdminTags(List<Long> tagNos) {
        int result = travelTagMapper.deleteTags(tagNos);
        if (result <= 0) {
            throw new DeleteException("Tag 삭제 실패");
        }
        return responseUtil.rd("200", tagNos, "Tag 삭제 성공");
    }
}
