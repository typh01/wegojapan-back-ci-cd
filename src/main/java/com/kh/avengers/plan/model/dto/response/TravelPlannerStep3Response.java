package com.kh.avengers.plan.model.dto.response;

import java.util.List;

import com.kh.avengers.plan.model.dto.SelectedPlaceDto;

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
public class TravelPlannerStep3Response {

  private Long planNo;
  private List<SelectedPlaceDto> selectedPlaces;
  private String message;
  private Integer totalSelectedCount;

}

