package com.kh.avengers.auth.controller;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.kh.avengers.auth.model.dto.EmailDTO;
import com.kh.avengers.auth.model.dto.FindIdRequestDTO;

import com.kh.avengers.auth.model.service.EmailService;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.member.model.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {
  private final EmailService emailService;

  // 회원가입
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
  //---------------------------------------------------------------------------------

  
  // 아이디 찾기 이메일 인증코드
  @PostMapping("/findVerify-code")
  public ResponseEntity<RequestData> findVerifyCode(@RequestBody Map<String, String> findCode){
    RequestData result = emailService.findVerifyCode(findCode);
    return ResponseEntity.ok(result);
  }

  // 아이디 찾기
  @PostMapping("/find-id")
  public ResponseEntity<RequestData> findId(@RequestBody @Valid FindIdRequestDTO findId){
    RequestData result = emailService.findId(findId);
    return ResponseEntity.ok(result);
  }
  //----------------------------------------------------------------------------------

  // 비밀번호 찾기
   @PostMapping("/find-password")
  public ResponseEntity<RequestData> findPassword(@RequestBody Map<String, String> findPw){
    RequestData result = emailService.findPassword(findPw);
    return ResponseEntity.ok(result);
  }

  // 비밀번호 찾기 이메일 인증코드
  @PostMapping("/findPassword-code")
  public ResponseEntity<RequestData> findPasswordCode(@RequestBody Map<String, String> findPwCode){
    RequestData result = emailService.findPasswordCode(findPwCode);
    return ResponseEntity.ok(result);
  }

  // 새 비밀 번호 입력
  @PostMapping("/new-password")
  public ResponseEntity<RequestData> newPassword(@RequestBody Map<String, String> newPw){
    RequestData result = emailService.newPassword(newPw);
    return ResponseEntity.ok(result);
  }
 
   
}
