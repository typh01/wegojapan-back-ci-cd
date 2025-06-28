package com.kh.avengers.auth.model.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.kh.avengers.auth.model.dto.FindIdRequestDTO;
import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.auth.model.vo.Login;
import com.kh.avengers.auth.model.vo.LoginInfo;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.member.model.dto.MemberDTO;
import com.kh.avengers.token.model.service.TokenService;
import com.kh.avengers.util.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
  
  private final AuthenticationManager authenticationManager;
  private final ResponseUtil responseUtil;
  private final TokenService tokenService;
  
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

    Map<String, Object> loginResponse = new HashMap<>();
    loginResponse.put("tokens",tokenService.generateToken(loginMember.getUsername(), loginMember.getMemberNo()));
    LoginInfo loginInfo = LoginInfo.builder()
                                    .memberNo(String.valueOf(loginMember.getMemberNo()))
                                    .username(loginMember.getUsername())
                                    .memberName(loginMember.getMemberName())
                                    .email(loginMember.getEmail())  
                                    .isActive(loginMember.getIsActive())
                                    .build();
    log.info("머임");
    loginResponse.put("loginInfo", loginInfo);
    return responseUtil.rd("200", loginResponse, "로그인 성공");
  }


}
