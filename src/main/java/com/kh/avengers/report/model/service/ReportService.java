package com.kh.avengers.report.model.service;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.report.model.dto.ReviewReportDTO;

public interface ReportService {

  RequestData reportReview(ReviewReportDTO report);

}