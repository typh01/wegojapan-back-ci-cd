package com.kh.avengers.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.admin.model.dto.AdminMemberDTO;


@Mapper
public interface AdminMapper {

  List<AdminMemberDTO> findAllMembers(Map<String, Object> pages);

  AdminMemberDTO getMemberById(Long memberNo);

  void updateMemberStatus(Long memberNo, String status);

  void updateMemberRole(Long memberNo, String role);

}
