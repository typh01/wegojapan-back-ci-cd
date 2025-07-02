package com.kh.avengers.plan.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep3Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep3Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class TravelPlannerStep3ServiceImpl implements TravelPlannerStep3Service {

  public TravelPlannerStep3Response updateStep3Plan(TravelPlannerStep3Request request, CustomUserDetails userDetails){

  log.info("여행플랜 step3 업데이트 시작 >> 플랜번호 : {}, 사용자 : {}, 선택된 여행지 개수 : {}", request.getPlanNo(), userDetails.getMemberName(), request.getSelectedPlaces().size());

    // 1. 플랜번호로 기존의 여행 플랜 조회


    // 2. 플랜이 존재하지 않는 경우


    // 3. 플랜의 소유저 확인( >> 본인의 플랜인지 확인)


    // 4. 선택된 지역이 있는지 확인(>> 즉, Step2가 완료되었는지 확인)


    // 5. 입력 데이터 유효성 검사


    // 6. 기존 선택된 여행지들 삭제(즉, 다시 돌아와서 수정하려고 할때?)


    // 7. 새로운 여행지를 선택한 순서대로 저장


    // 8. 선택된 여행지 정보를 JSON 형식으로 변환하여 TB_TRAVEL_PLANNER에도 저장..


    // 9. 응답생성



    return null;
  }


}
