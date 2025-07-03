package com.kh.avengers.plan.model.dto.request;

import jakarta.validation.constraints.NotBlank;
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
public class TravelPlannerStep2Request {

    @NotNull(message = "플랜 번호는 필수입니다.")
    private Long planNo;
    
    @NotBlank(message = "방문할 지역을 선택해주세요.")
    private String selectedRegion;

    
}
