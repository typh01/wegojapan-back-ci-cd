package com.kh.avengers.reviews.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImageDTO {

  private Long imageNo;
  private Long reviewNo;
  private String imageUrl;
  private LocalDateTime createdDate;

}
