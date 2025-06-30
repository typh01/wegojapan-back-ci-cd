package com.kh.avengers.token.model.vo;



import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshToken {
  private Long memberNo;
  private String refreshToken;
  private Long expried;


}
