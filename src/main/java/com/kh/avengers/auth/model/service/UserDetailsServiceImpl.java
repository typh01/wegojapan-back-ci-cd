package com.kh.avengers.auth.model.service;

import java.lang.reflect.Member;
import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.avengers.auth.model.dao.AuthMapper;
import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService{

  private final AuthMapper authMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

    MemberDTO member = authMapper.checkId(username);

    if(member == null){
      throw new UsernameNotFoundException("존재하지 않는 사용자 입니다.");
    }
    log.info("aa {}", member);
    return CustomUserDetails.builder()
                            .memberNo(member.getMemberNo())
                            .username(member.getMemberId())
                            .password(member.getMemberPw())
                            .email(member.getEmail())
                            .memberName(member.getMemberName())
                            .isActive(member.getIsActive())
                            .authorities(Collections.singletonList(new SimpleGrantedAuthority(member.getMemberRole())))
                            .build();

  }

}
