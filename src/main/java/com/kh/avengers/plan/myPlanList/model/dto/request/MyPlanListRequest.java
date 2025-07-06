package com.kh.avengers.plan.myPlanList.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPlanListRequest {

  @NotNull
  @Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다.")
  private Integer page;

  @NotNull(message = "페이지 크기는 필수입니다.")
  @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
  private Integer size;

  private String status; // 플랜의 상태 필터
  private String searchKeyword; // 검색 키워드

}
