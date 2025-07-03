package com.kh.avengers.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.kh.avengers.admin.model.dao.AdminMapper;
import com.kh.avengers.admin.model.dto.AdminMemberDTO;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.NotFoundException;

import com.kh.avengers.util.ResponseUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminServiceImpl implements AdminService{
  private final AdminMapper adminMapper;
  private final ResponseUtil responseUtil;
 
  
  @Override
  public RequestData selectMembers(int page, String status, String role) {
    int size = 5;
    int startIndex = (page - 1) * size;

    Map<String, Object> pages = new HashMap<>();
    pages.put("startIndex",startIndex);
    pages.put("size", size);
    pages.put("status", status);
    pages.put("role", role);

    List<AdminMemberDTO> members = adminMapper.findAllMembers(pages);
     return responseUtil.rd("200",members,"회원 목록 전체 조회 완료 ");
  }

  @Override
  public RequestData updateMemberStatus(Long memberNo, String status) {
    
    AdminMemberDTO member = adminMapper.getMemberById(memberNo);
    if(member == null){
      throw new NotFoundException("존재하지 않는 회원입니다.");
    }

    adminMapper.updateMemberStatus(memberNo, status);

    return responseUtil.rd("200", status, "회원 상태 변경되었습니다.");
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


 

}
