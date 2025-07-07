package com.kh.avengers.travels.model.dto;

import java.util.List;

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
public class UserTravelDTO {
    private Long travelNo;
    private Long categoryNo;
    private Long guNo;
    private String title;
    private String address;
    private String explain;
    private String discription;
    private String mapY;
    private String mapX;
    private String tel;
    private String email;
    private String website;
    private Long viewCount;
    private String categoryName;
    private String guName;
    private Double rating;

    private List<UserTravelImageDTO> imageList;
    private List<UserTravelTimeDTO> timeList;
    private List<UserTravelTagDTO> tagListForView;
    private List<UserTravelOptionDTO> optionListForView;
}
