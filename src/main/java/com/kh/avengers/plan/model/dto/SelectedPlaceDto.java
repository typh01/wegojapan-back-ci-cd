package com.kh.avengers.plan.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectedPlaceDto {

  private Long travelId;
  private Long planNo;
  private String travelName;
  private String travelDescription;
  private Double mapX;
  private Double mapY;
  private Integer choiceOrder;

}