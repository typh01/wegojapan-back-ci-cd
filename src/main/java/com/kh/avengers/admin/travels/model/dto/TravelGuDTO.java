package com.kh.avengers.admin.travels.model.dto;

import java.time.LocalDateTime;

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
  private Long cityNo;
  private String cityName;
  private Long guNo;
  private String guName;
  private String guMapY;
  private String guMapX;
  private LocalDateTime guCreatedDate;
  private LocalDateTime guModifiedDate;
  private String guStatus;
}
