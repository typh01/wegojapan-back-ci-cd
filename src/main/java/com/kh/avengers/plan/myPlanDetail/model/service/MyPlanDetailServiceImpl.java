package com.kh.avengers.plan.myPlanDetail.model.service;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.exception.util.ForbiddenException;
import com.kh.avengers.plan.model.dto.SelectedPlaceDto;
import com.kh.avengers.plan.myPlanDetail.model.dao.MyPlanDetailMapper;
import com.kh.avengers.plan.myPlanDetail.model.dto.MyPlanDetailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MyPlanDetailServiceImpl implements MyPlanDetailService{

  private final MyPlanDetailMapper myPlanDetailMapper;

  /**
   * 플랜 번호로 플랜 상세정보 조회
   * @param planNo 조회할 플랜 번호
   * @param userDetails 인증된 사용자의 정보
   * @return 플랜 상세 정보
   */
  @Override
  public MyPlanDetailDto getPlanDetail(Long planNo, CustomUserDetails userDetails){

    log.info("내플랜 상세 조회!! >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);

    // 1. 플랜 상세 정보 조회(>> 본인 플랜인지 확인)
    MyPlanDetailDto planDetail = myPlanDetailMapper.selectPlanDetailByPlanNoAndMemberNo(planNo, userDetails.getMemberNo());

    // 2. 플랜 존재x || 접근권한X
    if(planDetail == null){
      log.warn("플랜 조회 실패! >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);
      throw new ForbiddenException("해당 플랜을 찾을수 없거나 접근권한이 없습니다.");
    }

    // 3. 플랜에 연결된 선택된 여행지 목록 조회
    List<SelectedPlaceDto> selectedPlaces = myPlanDetailMapper.selectSelectedPlacesByPlanNo(planNo);
    log.debug("선택된 여행지 조회 완료!!! >> 여행지 개수 : {}", selectedPlaces.size());

    // 4. 플랜 상세 정보에 선택된 여행지 목록 설정
    planDetail.setSelectedPlaces(selectedPlaces);
    log.info("플랜 상세 조회 완료 >> 사용자 : {}, 플랜번호 : {}, 플랜제목 : {}, 여행지개수 : {}", userDetails.getMemberName(), planNo, planDetail.getPlanTitle(), selectedPlaces.size());
    return planDetail;
  }

  /**
   * 플랜 상세정보 수정
   * @param planDetailDto 플랜의 상세정보
   * @param userDetails 인증된 사용자 정보
   * @return 수정된 플랜
   */
  @Override
  public MyPlanDetailDto updatePlanDetail(MyPlanDetailDto planDetailDto, CustomUserDetails userDetails) {

    log.info("플랜정보 수정 요청 >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planDetailDto.getPlanNo());

    // 1. 플랜 소유자 확인
    MyPlanDetailDto existingPlan = myPlanDetailMapper.selectPlanDetailByPlanNoAndMemberNo(planDetailDto.getPlanNo(), userDetails.getMemberNo());

    // 2. 플랜 존재X || 플랜 소유자 X => 예외 발생
    if(existingPlan == null){
      log.warn("플랜 수정 실패!!! >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planDetailDto.getPlanNo());
      throw new ForbiddenException("해당 플랜을 찾을 수 없거나 접근권한이 없습니다.");
    }

    // 3. 회원번호
    planDetailDto.setMemberNo(userDetails.getMemberNo());
    
    // 4. 플랜 상세정보 수정
    int updateResult = myPlanDetailMapper.updatePlanDetail(planDetailDto);
    
    // 5. 확인
    if (updateResult == 0) {
      log.error("플랜 수정 실패! >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planDetailDto.getPlanNo());
      throw new RuntimeException("플랜 수정에 실패했습니다.");
    }

    log.info("플랜 수정 완료!! >> 사용자 : {}, 플랜번호 : {}, 플랜제목 : {}", userDetails.getMemberName(), planDetailDto.getPlanNo(), planDetailDto.getPlanTitle());
    
    // 6. 수정된 상세정보 반환
    return getPlanDetail(planDetailDto.getPlanNo(), userDetails);

  }

  /**
   * 플랜 삭제
   * @param planNo 삭제할 플랜의 고유 식별 번호
   * @param userDetails 인증된 사용자의 정보
   * @return 삭제 성공 여부
   */
  @Override
  public boolean deletePlan(Long planNo, CustomUserDetails userDetails){

    log.info("플랜 삭제 요청 >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);

    // 1. 플랜 존재 및 플랜의 소유자 확인
    MyPlanDetailDto existingPlan = myPlanDetailMapper.selectPlanDetailByPlanNoAndMemberNo(planNo, userDetails.getMemberNo());

    // 2. 플랜이 존재하지 않고나 소유자X => 예외 발생
    if (existingPlan == null) {
      log.warn("플랜 삭제 실패! >> 사용자 : {}, 플랜번호 : {} - 플랜이 존재하지 않거나 접근권한이 없습니다.", userDetails.getMemberName(), planNo);
      throw new ForbiddenException("해당 플랜을 찾을수 없거나 접근권한이 없습니다.");
    }

    try{
      // 3. 선택한 여행지 데이터 삭제
      int deletedPlaces = myPlanDetailMapper.deleteSelectedPlacesByPlanNo(planNo);
      log.debug("연관된 여행지 선택 삭제 완료!! >> 삭제된 여행지 개수 : {}", deletedPlaces);

      // 4. 플랜 삭제
      int deleteResult = myPlanDetailMapper.deletePlan(planNo, userDetails.getMemberNo());

      // 5. 결과 확인
      if (deleteResult == 0) {
        log.error("플랜 삭제 실패! >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);
        throw new RuntimeException("플랜 삭제에 실패했습니다.");
      }

      log.info("플랜 삭제 완료!! >> 사용자 : {}, 플랜번호 : {}", userDetails.getMemberName(), planNo);
      return true;

    } catch (Exception e){
      log.error("플랜 삭제 중 오류 발생!! >> 사용자 : {}, 플랜번호 : {}, 오류 : {}", userDetails.getMemberName(), planNo, e.getMessage());
      throw new RuntimeException("플랜 삭제 중 오류가 발생했습니다: " + e.getMessage());
    }

  }

}
