package com.kh.avengers.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.member.model.dao.MemberMapper;
import com.kh.avengers.member.model.dto.MemberDTO;
import com.kh.avengers.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kh.avengers.util.ResponseUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

  private final MemberMapper memberMapper;
  private final PasswordEncoder passwordEncoder;
  private final ResponseUtil responseUtil;

  @Override
  public RequestData signUp(MemberDTO member) {
    Long searchMember = memberMapper.getMemberByMemberId(member.getMemberId());
    MemberDTO searchEmail =  memberMapper.getMemberByEmail(member.getEmail());


    if(searchMember ==1 || searchEmail != null){
      throw new InvalidException("존재하는 아이디 또는 이메일 입니다.");
    }

    Member memberValue = Member.builder()
                               .memberId(member.getMemberId())
                               .memberPw(passwordEncoder.encode(member.getMemberPw()))
                               .memberName(member.getMemberName())
                               .email(member.getEmail())
                               .memberRole("ROLE_USER")
                               .build();

    memberMapper.signUp(memberValue);           
    
    return responseUtil.rd("200", null, "회원가입 성공"); 

  }


}
