package com.kh.avengers.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.auth.model.dto.FindIdRequestDTO;
import com.kh.avengers.auth.model.service.AuthService;
import com.kh.avengers.auth.model.vo.Login;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.token.model.service.TokenService;
import com.kh.avengers.token.model.vo.Tokens;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

  private final AuthService authService;
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<RequestData> login(@RequestBody @Valid Login member){
    log.info("하이하이");
    RequestData result = authService.login(member);
    return ResponseEntity.ok(result);
  }

}
