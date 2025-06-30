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
public class TravelThemaDTO {
  private Long themaNo; 
  private String themaName;
  private LocalDateTime themaCreatedDate;
  private LocalDateTime themaModifiedDate;
  private String themaStatus;
}
