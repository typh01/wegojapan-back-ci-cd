package com.kh.avengers.token.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster.Refresh;
import org.springframework.stereotype.Service;

import com.kh.avengers.configuration.util.JwtUtil;
import com.kh.avengers.token.dao.TokenMapper;
import com.kh.avengers.token.vo.RefreshToken;
import com.kh.avengers.token.vo.Tokens;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

  private final JwtUtil tokenUtil;
  private final TokenMapper tokenMapper;

  // 토큰 생성 메서드 호출 후 토큰 값을 Map으로 받아서 전달

  @Override
  public Tokens generateToken(String memberId, Long memberNo){
    Map<String, String> tokens = createToken(memberId);
    
    saveToken(tokens.get("refreshToken"), memberNo);

    return Tokens.builder()
                 .refreshToken(tokens.get("refreshToken"))
                 .accessToken(tokens.get("accessToken"))
                 .build();
  }

  private void saveToken(String refreshToken, Long memberNo) {

    RefreshToken token = RefreshToken.builder()
                                     .refreshToken(refreshToken)
                                     .memberNo(memberNo)
                                     .expiration(System.currentTimeMillis() + 3600000L * 24 * 3)
                                     .build();
                                     
    tokenMapper.saveToken(token);
    
  }

  private Map<String, String> createToken(String memberId) {
 
   String accessToken = tokenUtil.getAccessToken(memberId);
   String refreshToken = tokenUtil.getRefreshToken(memberId);

    Map<String, String> tokens = new HashMap();
    tokens.put("accessToken", accessToken);
    tokens.put("refreshToken", refreshToken);

      return tokens;
  }

  @Override
  public Tokens refreshToken(String refreshToken) {
    RefreshToken token = RefreshToken.builder().refreshToken(refreshToken).build();
    RefreshToken responseToken = tokenMapper.findByToken(token);

    if(responseToken == null || token.getExpiration() < System.currentTimeMillis()) {
			throw new RuntimeException("유효하지 않은 토큰입니다");
		}

    String memberId = getIdByToken(refreshToken);
    Long memberNo = responseToken.getMemberNo();
    return generateToken(memberId, memberNo);

  }

  private String getIdByToken(String refreshToken) {
    Claims claims = tokenUtil.parseJwt(refreshToken);
    return claims.getSubject();
  }
}
