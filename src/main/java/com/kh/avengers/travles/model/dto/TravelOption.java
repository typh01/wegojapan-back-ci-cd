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
public class TravelOption {
  private long travelOption;
  private long travelNo;
  private String travelOptionName;
  private String travelOptiontDetail;
  private Date travelOptionCreatedDate;
  private Date travelOptionModifiedDate;
}
