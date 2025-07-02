package com.kh.avengers.plan.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelChoiceDto {

  private Long choiceNo;
  private Long travelNo;
  private Long planNo;
  private Integer choiceOrder; // 여행지 방문 순서

}
