package com.kh.avengers.auth.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginInfo {

  private String memberNo;
  private String username;
  private String email;
  private String memberName;
  private char isActive;
  private final Collection<? extends GrantedAuthority> authorities;
}
