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
public class TravelCategoryDTO {
  private long travelCategoryNo;
  private String travelCategoryName;
  private Date travelCategoryCreatedDate;
  private Date travelCategoryModifiedDate;
}
