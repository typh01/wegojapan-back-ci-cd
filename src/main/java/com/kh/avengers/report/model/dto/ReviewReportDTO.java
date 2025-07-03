package com.kh.avengers.report.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ReviewReportDTO {
  private Long reviewNo;
  private Long memberNo;
  private String reportContent;

}
