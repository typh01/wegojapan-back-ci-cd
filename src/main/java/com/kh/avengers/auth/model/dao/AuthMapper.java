package com.kh.avengers.auth.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.member.model.dto.MemberDTO;

@Mapper
public interface AuthMapper {

  MemberDTO checkId(String username);

}
