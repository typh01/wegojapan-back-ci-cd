package com.kh.avengers.travles.model.dto;

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
public class TravelTagBridge {
  private Long bridgeNo;
  private Long travelNo;
  private Long tagNo;
}
