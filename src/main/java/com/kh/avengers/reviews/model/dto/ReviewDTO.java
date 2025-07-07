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

  private Boolean isLiked;     // 현재 사용자가 좋아요를 눌렀는지
  private Integer likeCount;

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
