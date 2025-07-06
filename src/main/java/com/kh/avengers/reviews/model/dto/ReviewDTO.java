package com.kh.avengers.reviews.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

  private Long reviewNo;
  private Long travelNo;
  private Long memberNo;
  private String reviewTitle;
  private String reviewContent;
  private Integer rating;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

  private String travelName;
  private String memberName;

  private List<ReviewImageDTO> imageList;

}
