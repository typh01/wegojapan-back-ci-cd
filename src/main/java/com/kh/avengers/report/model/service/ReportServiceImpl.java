package com.kh.avengers.report.model.service;

import org.springframework.stereotype.Service;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.NotFoundException;
import com.kh.avengers.exception.util.InvalidAccessException;
import com.kh.avengers.report.model.dao.ReportMapper;
import com.kh.avengers.report.model.dto.ReviewReportDTO;
import com.kh.avengers.util.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService{
  
  private final ReportMapper reportMapper;
  private final ResponseUtil responseUtil;
  
  
  @Override
  public RequestData reportReview(ReviewReportDTO report) {

    if(reportMapper.findReview(report.getReviewNo()) == 0){
      throw new NotFoundException("리뷰가 존재하지 않습니다.");
    }

    if(reportMapper.findMember(report.getMemberNo()) == 0){
      throw new NotFoundException(("존재하지 않는 회원입니다."));
    }

    if(reportMapper.checkedReport(report.getReviewNo(), report.getMemberNo()) == 1){
      throw new InvalidAccessException("이미 신고한 리뷰입니다.");
    }
     reportMapper.insertReviewReport(report);

    return responseUtil.rd("200", report,"신고가 성공적으로 되었습니다.");
  }

}
