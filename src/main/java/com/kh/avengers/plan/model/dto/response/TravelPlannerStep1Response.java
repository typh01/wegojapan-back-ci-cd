package com.kh.avengers.plan.model.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelPlannerStep1Response {

    private Long planNo;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer travelers;
    
}
