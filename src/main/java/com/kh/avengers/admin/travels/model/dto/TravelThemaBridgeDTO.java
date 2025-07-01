package com.kh.avengers.admin.travels.model.dto;

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
public class TravelThemaBridgeDTO {
    private Long bridgeNo; // 브릿지 번호
    private Long travelNo; // 여행지 번호
    private Long themaNo; // 테마 번호
    private String themaName; // 테마 이름
}
