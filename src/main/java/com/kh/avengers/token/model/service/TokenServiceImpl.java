package com.kh.avengers.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.avengers.configuration.util.JwtUtil;
import com.kh.avengers.token.model.dao.TokenMapper;
import com.kh.avengers.token.model.vo.RefreshToken;
import com.kh.avengers.token.model.vo.Tokens;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

  private final JwtUtil tokenUtil;
  private final TokenMapper tokenMapper;

  // 토큰 생성 메서드 호출 후 토큰 값을 Map으로 받아서 전달

  @Override
  public Tokens generateToken(String memberId, Long memberNo){
    log.info(" generateToken 호출됨 - memberId: {}, memberNo: {}", memberId, memberNo);
    Map<String, String> tokens = createToken(memberId);
    log.info(" 생성된 AccessToken: {}", tokens.get("accessToken"));
    log.info(" 생성된 RefreshToken: {}", tokens.get("refreshToken"));
    
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
                                     .expried(System.currentTimeMillis() + (3600000L * 24 * 3))
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

    if(responseToken == null || token.getExpried() < System.currentTimeMillis()) {
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
