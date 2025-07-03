package com.kh.avengers.plan.model.dto.request;

import com.kh.avengers.plan.model.dto.SelectedPlaceDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelPlannerStep3Request {

  @NotNull(message = "플랜 번호는 필수입니다.")
  private Long planNo;

  @NotEmpty(message = "최소 1개 이상의 여행지를 선택해주세요.")
  @Size(min = 1, max = 5, message = "여행지는 최소 1개, 최대 5개까지 선택가능합니다.")
  @Valid
  private List<SelectedPlaceDto> selectedPlaces;

}
