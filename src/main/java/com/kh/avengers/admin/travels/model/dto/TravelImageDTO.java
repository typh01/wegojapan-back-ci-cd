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
public class TravelImageDTO {
    private Long imageNo;
    private Long travelNo;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
