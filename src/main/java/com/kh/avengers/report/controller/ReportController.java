package com.kh.avengers.report.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.report.model.dto.ReviewReportDTO;
import com.kh.avengers.report.model.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {

  private final ReportService reportService;

  @PostMapping("/review")
  public ResponseEntity<RequestData> reportReview(@RequestBody ReviewReportDTO report){

    RequestData result = reportService.reportReview(report);
    return ResponseEntity.ok(result);

  }


}
