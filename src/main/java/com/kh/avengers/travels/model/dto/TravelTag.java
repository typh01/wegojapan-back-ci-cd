package com.kh.avengers.travles.model.dto;

import java.sql.Date;

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
public class TravelTag {
  private Long tagNo;
  private String tagName;
  private Date tagCreatedDate;
  private Date tagModifiedDate;
}
