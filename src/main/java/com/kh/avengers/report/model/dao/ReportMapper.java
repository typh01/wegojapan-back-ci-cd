package com.kh.avengers.report.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.report.model.dto.ReviewReportDTO;

@Mapper
public interface ReportMapper {

  int findReview(Long reviewNo);

  int findMember(Long memberNo);

  int checkedReport(Long reviewNo, Long memberNo);

  void insertReviewReport(ReviewReportDTO report);

}