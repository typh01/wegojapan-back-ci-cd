package com.kh.avengers.plan.model.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class TravelPlannerStep1Request {

    @NotNull(message = "여행 시작일을 선택해주세요.")
    private LocalDate startDate;

    @NotNull(message = "여행 종료일을 선택해주세.")
    private LocalDate endDate;

    @NotNull(message = "여행 인원을 입력해주세요.")
    @Min(value = 1, message = "여행 인원은 최소 1명이어야 합니다.")
    @Max(value = 20, message = "여행 인원은 최대 20명까지 가능합니다.")
    private Integer travelers;
    
}
