package com.kh.avengers.admin.travels.model.dto;

import java.time.LocalDateTime;

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
public class TravelCategoryDTO {
  private Long categoryNo;
  private String categoryName;
  private LocalDateTime categoryCreatedDate;
  private LocalDateTime categoryModifiedDate;
  private String categoryStatus;
}
