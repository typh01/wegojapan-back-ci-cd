package com.kh.avengers.plan.myPlanDetail.model.dto;

import com.kh.avengers.plan.model.dto.SelectedPlaceDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPlanDetailDto {

  private Long planNo;
  private String planTitle;
  private String planDescription;
  private String planStatus;
  private LocalDate travelStartDate;
  private LocalDate travelEndDate;
  private Integer groupSize;
  private Long minBudget;
  private Long maxBudget;
  private String transportReservationLink;
  private String accommodationLink;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;
  private List<SelectedPlaceDto> selectedPlaces;

}
