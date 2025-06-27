package com.kh.avengers.admin.travels.model.dto;

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
  private String cityMapX;
  private String cityMapY;
  private Date cityCreatedDate;
  private Date cityModifiedDate;
  private String cityStatus;
}
