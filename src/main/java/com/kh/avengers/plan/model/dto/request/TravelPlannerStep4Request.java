package com.kh.avengers.plan.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class TravelPlannerStep4Request {
    
    @NotNull(message = "플랜번호는 필수입니다.")
    private Long planNo;

    @NotBlank(message = "플랜제목은 필수입니다.")
    @Size(min = 2, max = 30, message = "플랜 제목은 최소 2글자 이상, 최대 30글자까지 가능합니다.")
    private String planTitle;

    @Size(max = 500, message = "플랜 설명은 최대 500자까지 가능합니다.")
    private String planDescription;

    @Min(value = 0, message = "예산은 0원 이상이어야 합니다.")
    private Integer minBudget;

    @Min(value = 0, message = "예산은 0원 이상이어야 합니다.")
    private Integer maxBudget;

    private String flightLink;
    private String hotelLink;


}
