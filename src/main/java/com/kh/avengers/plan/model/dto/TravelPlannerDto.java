package com.kh.avengers.plan.model.dto;

import java.time.LocalDate;

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
public class TravelPlannerDto {

    private Long planNo;
    private Long memberNo;
    private String planTitle;
    private String planDescription;
    private LocalDate travelStartDate;
    private LocalDate travelEndDate;
    private Integer groupSize;
    private Integer minBudget;
    private Integer maxBudget;
    private String transportReservationLink;
    private String accommodationLink;

}
