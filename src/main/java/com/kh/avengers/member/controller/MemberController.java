package com.kh.avengers.member.controller;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.auth.model.dto.UpdatePasswordDTO;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.member.model.dto.ChangeMemberNameDTO;
import com.kh.avengers.member.model.dto.MemberDTO;
import com.kh.avengers.member.model.service.MemberService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @PostMapping
  public ResponseEntity<RequestData> signUp(@RequestBody @Valid MemberDTO member){

    RequestData result = memberService.signUp(member); 
    return ResponseEntity.ok(result);
  }

  @GetMapping("/checkedMemberName")
  public ResponseEntity<RequestData> checkName(@RequestParam(name="newMemberName") String member){
     log.info(" 닉네임 중복 확인 요청: {}", member);
    RequestData result = memberService.checkName(member);
    return ResponseEntity.ok(result);
  }

  @PatchMapping("/changeName")
  public ResponseEntity<RequestData> updateMember(@RequestBody @Valid ChangeMemberNameDTO member){

    RequestData result = memberService.updateMember(member);
    return ResponseEntity.ok(result);
  }

  

  @PatchMapping("/changePassword")
  public ResponseEntity<RequestData> updatePassword(@RequestBody Map<String, String> member){

    RequestData result = memberService.updatePassword(member);
    return ResponseEntity.ok(result);
  }

  @DeleteMapping("/deleteMember")
  public ResponseEntity<RequestData> deleteMember(@RequestBody Map<String, String> member ){
    
    RequestData result = memberService.deleteMember(member);
    return ResponseEntity.ok(result);
  }

}
