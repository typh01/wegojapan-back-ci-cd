package com.kh.avengers.admin.travels.model.dto;


import java.time.LocalDateTime;
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
public class TravelDTO {
    private Long travelNo;
    private Long guNo;
    private Long categoryNo;
    private String title;
    private String explain;
    private String description;
    private String address;
    private String mapY;
    private String mapX;
    private String tel;
    private String email;
    private String website;
    private String status;
    private String guName;
    private String categoryName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String travelImage;
    private Long viewCount;
    private Double rating;

    private List<TravelTimeDTO> timeList;
    private List<TravelImageDTO> imageList;
    private List<TravelTagBridgeDTO> tagList;
    private List<TravelThemaBridgeDTO> themaList;
    private List<TravelOptionBridgeDTO> optionList;
    
    private List<TravelTagDTO> tagListForView;
    private List<TravelThemaDTO> themaListForView;
    private List<TravelOptionDTO> optionListForView;
}