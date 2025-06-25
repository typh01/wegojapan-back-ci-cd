package com.kh.avengers.token.service;

import com.kh.avengers.token.vo.Tokens;

public interface TokenService {
  // 토큰 생성
    Tokens generateToken(String memberId, Long memberNo);

    Tokens refreshToken(String refreshToken);

}
