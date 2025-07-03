package com.kh.avengers.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.kh.avengers.admin.model.dao.AdminMapper;
import com.kh.avengers.admin.model.dto.AdminMemberDTO;
import com.kh.avengers.admin.model.dto.AdminReviewReportDTO;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.NotFoundException;

import com.kh.avengers.util.ResponseUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AdminServiceImpl implements AdminService{
  private final AdminMapper adminMapper;
  private final ResponseUtil responseUtil;
 
  
  @Override
  public RequestData selectMembers(int page, String status, String role) {
    int size = 5;
    int startIndex = (page - 1) * size;

    RowBounds rowBounds = new RowBounds(startIndex, size);

    List<AdminMemberDTO> members = adminMapper.findAllMembers(rowBounds);
     return responseUtil.rd("200",members,"회원 목록 전체 조회 완료 ");
  }

  

  @Override
  public RequestData updateMemberRole(Long memberNo, String role) {
    AdminMemberDTO member = adminMapper.getMemberById(memberNo);

    if(member == null){
      throw new NotFoundException("존재하지 않는 회원입니다.");
    }
    
    adminMapper.updateMemberRole(memberNo, role);

    return responseUtil.rd("200", role, "회원 권한 업데이트 완료 되었습니다.");
  }

  @Override
  public RequestData reportMember(int page) {
    int size = 5;
    int startIndex = (page - 1) * size;

    RowBounds rowBounds = new RowBounds(startIndex, size);

    List<AdminReviewReportDTO> reportList = adminMapper.selectReportMembers(rowBounds);

    return responseUtil.rd("200", reportList, "신고된 리뷰조회 성공했습니다." );
  }



 

}
