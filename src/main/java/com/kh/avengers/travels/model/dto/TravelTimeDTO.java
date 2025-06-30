package com.kh.avengers.travels.model.dto;

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
public class TravelTimeDTO {
  private long travelTimeNo;
  private long travelNo;
  private String travelDayOfWeek;
  private String travelStartTime;
  private String travelEndTime;
  private Date travelTimeCreatedDate;
  private Date travelTimeModifiedDate;
}
