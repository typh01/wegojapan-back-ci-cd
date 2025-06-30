package com.kh.avengers.admin.travels.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.avengers.admin.travels.model.dao.TravelCategoryMapper;
import com.kh.avengers.admin.travels.model.dto.TravelCategoryDTO;
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
public class AdminTravelCategoryServiceImpl implements AdminTravelCategoryService {

    private final TravelCategoryMapper travelCategoryMapper;
    private final ResponseUtil responseUtil;

    private void checkCategoryExists(long categoryNo) {
        if (travelCategoryMapper.searchCategoryNo(categoryNo) <= 0) {
            throw new NotFoundException("Category를 찾지 못했습니다.");
        }
    }

    @Override
    public RequestData getTravelCategories(TravelCategoryDTO categoryDTO) {
        List<TravelCategoryDTO> categoryList = travelCategoryMapper.getCategoryList(categoryDTO);
        return responseUtil.rd("200", categoryList, "Category 목록 조회 완료");
    }

    @Override
    public RequestData getAdminCategories(TravelCategoryDTO categoryDTO) {
        List<TravelCategoryDTO> categoryList = travelCategoryMapper.getAdminCategory(categoryDTO);
        return responseUtil.rd("200", categoryList, "관리자 Category 목록 조회 완료");
    }

    @Override
    public RequestData postAdminCategory(TravelCategoryDTO categoryDTO) {
        int result = travelCategoryMapper.insertCategory(categoryDTO);
        if (result <= 0) {
            throw new InvalidException("Category 등록 실패");
        }
        return responseUtil.rd("200", categoryDTO, "Category 등록 성공");
    }

    @Override
    public RequestData updateAdminCategory(TravelCategoryDTO categoryDTO) {
        checkCategoryExists(categoryDTO.getCategoryNo());
        int result = travelCategoryMapper.updateCategory(categoryDTO);
        if (result <= 0) {
            throw new UpdateException("Category 수정 실패");
        }
        return responseUtil.rd("200", categoryDTO, "Category 수정 성공");
    }

    @Override
    public RequestData deleteAdminCategories(List<Long> categoryNos) {
        int result = travelCategoryMapper.deleteCategories(categoryNos);
        if (result <= 0) {
            throw new DeleteException("Category 삭제 실패");
        }
        return responseUtil.rd("200", categoryNos, "Category 삭제 성공");
    }
}
