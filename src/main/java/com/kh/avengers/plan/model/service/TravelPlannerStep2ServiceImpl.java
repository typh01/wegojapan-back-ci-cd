package com.kh.avengers.plan.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.exception.util.ForbiddenException;
import com.kh.avengers.plan.model.dao.TravelPlannerMapper;
import com.kh.avengers.plan.model.dto.TravelPlannerDto;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep2Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep2Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TravelPlannerStep2ServiceImpl implements TravelPlannerStep2Service {

    private final TravelPlannerMapper travelPlannerMapper;

    /**
     * step2플랜 업데이트(선택한 지역 저장하기)
     * @param request  step2에서 정한 지역 정보
     * @param userDetails 인증된 사용자의 정보
     * @return 응답 정보
     */
    @Override
    @Transactional
    public TravelPlannerStep2Response updateStep2Plan(TravelPlannerStep2Request request, CustomUserDetails userDetails){
        
        log.info("여행플랜 step2 업데이트 시작!! >> 플랜번호 : {}, 사용자 : {}, 선택지역 : {}", request.getPlanNo(), userDetails.getMemberName(), request.getSelectedRegion());

        // 1. 플랜 번호로 기존 여행 플랜 조회
        TravelPlannerDto existingPlan = travelPlannerMapper.selectTravelPlanByPlanNo(request.getPlanNo());
        
        // 2. 플랜이 존재하지 않는 경우
        if (existingPlan == null){
            log.error("존재하지 않는 플랜 번호 >> {}",request.getPlanNo());
            throw new InvalidException("해당 플랜을 찾을 수 없습니다.");
        }

        // 3. 플랜의 소유자 확인(>> 본인의 플랜인지 확인)
        if(!existingPlan.getMemberNo().equals(userDetails.getMemberNo())){
            log.error("플랜 소유저 불일치!!! >> 플랜 소유자 : {}, 요청자 : {}", existingPlan.getMemberNo(), userDetails.getMemberNo());
            throw new ForbiddenException("해당 플랜에 대한 접근 권한이 없습니다.");
        }


        // 4. 업데이트할 데이터 준비
        TravelPlannerDto updateDto = TravelPlannerDto.builder()
                                                     .planNo(request.getPlanNo())
                                                     .selectRegion(request.getSelectedRegion())
                                                     .build();

        // 5. DB에 step2 정보를 업데이트
        int updateResult = travelPlannerMapper.updateTravelPlanStep2(updateDto);

        // 6. 업데이트 결과 확인
        if(updateResult != 1){
            log.error("여행플랜 step2 업데이트 실패 >> 플랜번호 : {}", request.getPlanNo());
            throw new InvalidException("여행 플랜 업데이트에 실패했습니다.");
        }

        // 7. 성공
        log.info("step2 여행 플랜 업데이트 완료 >> 플랜번호 : {}, 선택지역 : {}", request.getPlanNo(), request.getSelectedRegion());

        // 8. 응답 DTO 생성
        return TravelPlannerStep2Response.builder()
                                         .planNo(request.getPlanNo())
                                         .selectedRegion(request.getSelectedRegion())
                                         .message("지역 선택이 완료되었슴돠~")
                                         .build();

    }
    
}
