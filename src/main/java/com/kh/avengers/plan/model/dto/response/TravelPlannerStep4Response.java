package com.kh.avengers.plan.model.dto.response;

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
public class TravelPlannerStep4Response {
    
    private Long planNo;
    private String planTitle;
    private String planDescription;
    private Integer minBudget;
    private Integer maxBudget;
    private String transportReservationLink;
    private String accommodationLink;
    private String message;


}
