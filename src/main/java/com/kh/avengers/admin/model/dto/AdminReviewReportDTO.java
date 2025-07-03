package com.kh.avengers.admin.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminReviewReportDTO {
  private Long reportNo;
  private String reviewContent;
  private String memberName;
  private String reportReason;
  private Date createDate;
  private String status;

}
