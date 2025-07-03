package com.kh.avengers.plan.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.exception.util.ForbiddenException;
import com.kh.avengers.plan.model.dao.TravelChoiceMapper;
import com.kh.avengers.plan.model.dao.TravelPlannerMapper;
import com.kh.avengers.plan.model.dto.SelectedPlaceDto;
import com.kh.avengers.plan.model.dto.TravelChoiceDto;
import com.kh.avengers.plan.model.dto.TravelPlannerDto;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep3Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep3Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TravelPlannerStep3ServiceImpl implements TravelPlannerStep3Service {

  private final TravelPlannerMapper travelPlannerMapper;
  private final TravelChoiceMapper travelChoiceMapper;
  /**
   * step3 플랜 업데이트
   * @param request step3에서 선택한 여행지 정보
   * @param userDetails 인증된 사용자의 정보
   * @return 응답 정보
   */
  @Override
  public TravelPlannerStep3Response updateStep3Plan(TravelPlannerStep3Request request, CustomUserDetails userDetails){

    log.info("여행플랜 step3 업데이트 시작 >> 플랜번호 : {}, 사용자 : {}, 선택된 여행지 개수 : {}", request.getPlanNo(), userDetails.getMemberName(), request.getSelectedPlaces().size());

    // 1. 플랜번호로 기존의 여행 플랜 조회
    TravelPlannerDto existingPlan = travelPlannerMapper.selectTravelPlanByPlanNo(request.getPlanNo());

    // 2. 플랜이 존재하지 않는 경우
    if(existingPlan == null){
      log.error("존재하지 않는 플랜 번호 >> {}", request.getPlanNo());
      throw new InvalidException("해당 플랜을 찾을 수 없습니다.");
    }

    // 3. 플랜의 소유자 확인( >> 본인의 플랜인지 확인)
    if(!existingPlan.getMemberNo().equals(userDetails.getMemberNo())){
      log.error("플랜의 소유자 불일치 >> 플랜 소유자 : {}, 요청자 : {}", existingPlan.getMemberNo(), userDetails.getMemberNo());
      throw new ForbiddenException("해당 플랜에 대한 접근 권한이 없습니다.");
    }

    // 4. 선택된 지역이 있는지 확인(>> 즉, Step2가 완료되었는지 확인)
    if(existingPlan.getSelectRegion() == null || existingPlan.getSelectRegion().trim().isEmpty()){
      log.error("지역선택이 되지 않은 플랜 >> 플랜번호 : {}", request.getPlanNo());
      throw new InvalidException("먼저 지역을 선택해주세요.");
    }

    // 5. 입력 데이터 유효성 검사 (기존 로직 유지)
    validateStep3Request(request);

    // 6. 기존 선택된 여행지들 삭제(즉, 다시 돌아와서 수정하려고 할때?)
    int deletedCount = travelChoiceMapper.deleteTravelChoices(request.getPlanNo());
    log.info("기존의 선택된 여행지 삭제 완료 >> 플랜번호 : {}, 삭제된 개수 : {}", request.getPlanNo(), deletedCount);

    // 7. 새로운 여행지를 선택한 순서대로 저장
    List<SelectedPlaceDto> selectedPlaces = request.getSelectedPlaces();

    IntStream.range(0, selectedPlaces.size())
            .forEach(index -> {
              SelectedPlaceDto place = selectedPlaces.get(index);

              // TravelChoiceDto 생성 (순서는 1부터 시작)
              TravelChoiceDto travelChoiceDto = TravelChoiceDto.builder()
                      .travelNo(place.getTravelId())
                      .planNo(request.getPlanNo())
                      .choiceOrder(index + 1)
                      .build();

              // DB에 저장
              int insertResult = travelChoiceMapper.insertTravelChoice(travelChoiceDto);

              if (insertResult != 1) {
                log.error("여행지 선택 정보 저장 실패! >> 여행지 ID: {}, 순서: {}",
                        place.getTravelId(), index + 1);
                throw new InvalidException("여행지 선택 정보 저장에 실패했습니다.");
              }

              log.debug("여행지 선택 정보 저장 완료 >> 여행지: {}, 순서: {}",
                      place.getTravelName(), index + 1);
            });

    // 8. 응답 생성
    log.info("step3 여행 플랜 업데이트 완료!!!! >> 플랜번호: {}, 선택된 여행지 개수: {}",
            request.getPlanNo(), selectedPlaces.size());

    return TravelPlannerStep3Response.builder()
            .planNo(request.getPlanNo())
            .selectedPlaces(selectedPlaces)
            .totalSelectedCount(selectedPlaces.size())
            .message("여행지 선택이 완료되었습니다!!!!")
            .build();
  }

  /**
   * step3 플랜 요청 데이터의 유효성검사
   * @param request 검사할 step3의 요청 데이터
   */
  public void validateStep3Request(TravelPlannerStep3Request request){

    List<SelectedPlaceDto> selectedPlaces = request.getSelectedPlaces();

    // 선택된 여행지의 개수 검사
    if(selectedPlaces == null || selectedPlaces.isEmpty()){
      log.warn("선택된 여행지가 없습니다. >> 플랜번호 : {}", request.getPlanNo());
      throw new InvalidException("최소 1개 이상의 여행지를 선택해주세요.");
    }

    if(selectedPlaces.size() > 5){
      log.warn("선택된 여행지가 너무 많습니다. >> 플랜번호 : {}, 개수 : {}", request.getPlanNo(), selectedPlaces.size());
      throw new InvalidException("최대 5개까지만 선택해주세요.");
    }

    // 각 여행지에 대한 유효성 검사
    for(int i = 0; i < selectedPlaces.size(); i++){
      SelectedPlaceDto place = selectedPlaces.get(i);

      if(place.getTravelId() == null || place.getTravelId() <= 0){
        log.warn("유효하지 않은 여행지 ID가 포함되어 있습니다. >> 플랜번호 : {}", request.getPlanNo());
        throw new InvalidException("유효하지 않은 여행지 정보가 있습니다.");
      }

      if(place.getTravelName() == null || place.getTravelName().trim().isEmpty()){
        log.warn("여행지명이 없습니다 >> 순서 : {}, ID : {}", i+1, place.getTravelId());
        throw new InvalidException("여행지명이 존재하지 않습니다.");
      }

      if(place.getMapX() == null || place.getMapY() == null){
        log.warn("여행지에 대한 좌표 정보가 없습니다. >> 순서 : {}, ID : {}", i+1, place.getTravelId());
        throw new InvalidException("여행지의 위치정보가 없습니다.");
      }
    }
  }
}
