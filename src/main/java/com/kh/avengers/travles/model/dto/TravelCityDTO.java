package com.kh.avengers.travles.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelCityDTO {
  private Long cityNo;
  private String cityName;
  private Date cityCreatedDate;
  private Date cityModifieDate;
  private String cityStatus;
}
