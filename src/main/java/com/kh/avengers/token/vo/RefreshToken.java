package com.kh.avengers.token.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshToken {
  private Long memberNo;
  private String refreshToken;
  private Long expiration;


}
