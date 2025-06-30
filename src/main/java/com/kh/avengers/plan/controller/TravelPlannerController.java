package com.kh.avengers.plan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep1Request;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/travel-planner")
public class TravelPlannerController {
    
    @PostMapping("/step1")
    public ResponseEntity<RequestData> createStep1Plan(
        @RequestBody @Valid TravelPlannerStep1Request request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {


            return null;
        }
    

}
