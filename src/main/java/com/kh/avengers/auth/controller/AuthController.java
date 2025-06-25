package com.kh.avengers.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.auth.model.service.AuthService;
import com.kh.avengers.auth.model.vo.Login;
import com.kh.avengers.common.dto.RequestData;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<RequestData> login(@RequestBody @Valid Login member){
    RequestData token = authService.login(member);
    log.info("토큰 잘옴?{}", token);
    return ResponseEntity.ok(token);
  }

}
