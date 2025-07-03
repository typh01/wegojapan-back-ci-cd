package com.kh.avengers.plan.model.dto;

import lombok.*;

/**
 * 앞단에서 받는 선택된 여행지 정보
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectedPlaceDto {
  private Long travelId;       // 여행지 시퀀스 ID
  private String travelName;   // 여행지 이름
  private Double mapX;    // 위도
  private Double mapY;   // 경도
  private String travelDescription; // 여행지 설명
  
}
