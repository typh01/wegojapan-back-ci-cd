package com.kh.avengers.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.member.model.dto.MemberDTO;
import com.kh.avengers.member.model.service.MemberService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @PostMapping
  public ResponseEntity<RequestData> signUp(@RequestBody @Valid MemberDTO member){

    RequestData result = memberService.signUp(member); 
    return ResponseEntity.ok(result);
  }



}
