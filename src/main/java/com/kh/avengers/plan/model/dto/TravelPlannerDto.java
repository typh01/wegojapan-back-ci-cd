package com.kh.avengers.plan.model.dto;

import java.time.LocalDate;
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
public class TravelPlannerDto {

    private Long planNo;
    private Long memberNo;

    // step1에서 입력받은 정보
    private LocalDate travelStartDate;
    private LocalDate travelEndDate;
    private Integer groupSize;

    // step2에서 입력받은 정보
    private String selectRegion;

    // step3 -- 수정할수도
    private String selectPlaces;

    // step4에서 입력받은 정보
    private String planTitle;
    private String planDescription;
    private Integer minBudget;
    private Integer maxBudget;
    private String transportReservationLink;
    private String accommodationLink;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
