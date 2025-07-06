package com.kh.avengers.plan.myPlanList.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPlanListDto {

  private Long planNo;
  private String planTitle;
  private String planStatus;
  private String selectedRegion;
  private LocalDate travelStartDate;
  private LocalDate travelEndDate;
  private Integer groupSize;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;

}
