package com.kh.avengers.auth.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.kh.avengers.auth.model.dto.EmailDTO;
import com.kh.avengers.auth.model.service.EmailService;
import com.kh.avengers.common.dto.RequestData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {
  private final EmailService emailService;

  @PostMapping("/send-email")
  public ResponseEntity<RequestData> signUpEmailCode(@RequestBody Map<String, String> email){
    RequestData result = emailService.signUpEmailCode(email);

    return ResponseEntity.ok(result);
  }

  @PostMapping("/verify-code")
  public ResponseEntity<RequestData> checkVerifyCode(@RequestBody @Valid EmailDTO code){
    RequestData result = emailService.checkVerifyCode(code);

    return ResponseEntity.ok(result);
  } 
}
