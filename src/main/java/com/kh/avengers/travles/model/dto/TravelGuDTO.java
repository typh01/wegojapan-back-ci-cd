package com.kh.avengers.travles.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelGuDTO {
  private Long guNo;
  private Long cityNo;
  private String guName;
  private Date guCreatedDate;
  private Date guModifiedDate;
  private String guStatus;
}
