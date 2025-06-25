package com.kh.avengers.auth.model.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.auth.model.vo.Login;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.member.model.dto.MemberDTO;
import com.kh.avengers.util.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
  
  private final AuthenticationManager authenticationManager;
  private final ResponseUtil responseUtil;
  
  @Override
  public RequestData login(Login member) {
    
    Authentication authentication = null;

    try{
      authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getMemberId(), 
                                                                                                  member.getMemberPw()));
    } catch(AuthenticationException e){
      throw new InvalidException("아이디 또는 비밀번호를 잘못 입력했습니다.");
    }
    CustomUserDetails loginMember = (CustomUserDetails)authentication.getPrincipal();

    
  

    return responseUtil.rd("200", loginMember, "로그인 성공");
  }

}
