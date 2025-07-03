package com.kh.avengers.admin.travels.model.dto;


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
    private List<TravelTimeDTO> timeList;
    private List<TravelImageDTO> imageList;
    private List<TravelTagDTO> tagListForView;
    private List<TravelTagBridgeDTO> tagList;
    private List<TravelOptionDTO> optionListForView;
    private List<TravelOptionBridgeDTO> optionList;
    private List<TravelThemaDTO> themaListForView;
    private List<TravelThemaBridgeDTO> themaList;
    private String travelImage;
}