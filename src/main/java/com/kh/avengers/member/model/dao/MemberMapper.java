package com.kh.avengers.member.model.dao;

import org.apache.ibatis.annotations.Mapper;


import com.kh.avengers.member.model.vo.Member;


@Mapper
public interface MemberMapper {

  Long getMemberByMemberId(String memberId);

  Long getMemberByEmail(String email);

  void signUp(Member memberValue);

}
