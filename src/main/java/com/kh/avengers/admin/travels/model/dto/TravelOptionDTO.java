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
public class TravelOptionDTO {
  private Long optionNo; 
  private String optionName;
  private LocalDateTime optionCreatedDate;
  private LocalDateTime optionModifiedDate;
  private String optionStatus;
}
