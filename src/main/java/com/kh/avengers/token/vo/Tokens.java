package com.kh.avengers.token.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Tokens {
  private String accessToken;
  private String refreshToken;

}
