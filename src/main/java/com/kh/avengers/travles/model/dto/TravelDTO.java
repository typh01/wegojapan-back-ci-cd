package com.kh.avengers.travles.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelDTO {
  private long travelNo;
  private long travelGuNo;
  private long travelCategoryNo;
  private String travelTitle;
  private String travelExplain;
  private String travelDescription;
  private String travelAddress;
  private String travelMapY;
  private String travelMapX;
  private String travelTel;
  private Date travelCreatedDate;
  private Date travelModifiedDate;
  private String travelStatus;
}
