package com.kh.avengers.token.model.service;

import com.kh.avengers.token.model.vo.RefreshToken;
import com.kh.avengers.token.model.vo.Tokens;

public interface TokenService {
  // 토큰 생성
    Tokens generateToken(String memberId, Long memberNo);

    Tokens refreshToken(String refreshToken);

}
