package com.kh.avengers.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.auth.model.dto.FindIdRequestDTO;
import com.kh.avengers.auth.model.dto.UpdatePasswordDTO;
import com.kh.avengers.member.model.dto.MemberDTO;
import com.kh.avengers.member.model.vo.Member;


@Mapper
public interface MemberMapper {

  Long getMemberByMemberId(String memberId);

  MemberDTO getMemberByEmail(String email);

  void signUp(Member memberValue);

  FindIdRequestDTO findMemberByNameAndEmail(String memberName, String email);

  void newPassword(UpdatePasswordDTO updatePw);

  void updateMemberPassword(UpdatePasswordDTO updatePw);

}
