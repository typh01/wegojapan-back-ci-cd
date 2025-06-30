package com.kh.avengers.admin.travels.model.dto;

import java.time.LocalDateTime;

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
  private LocalDateTime cityCreatedDate;
  private LocalDateTime cityModifiedDate;
  private String cityStatus;
}
