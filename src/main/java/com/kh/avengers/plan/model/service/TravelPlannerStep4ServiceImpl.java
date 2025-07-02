package com.kh.avengers.plan.model.service;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.exception.util.ForbiddenException;
import com.kh.avengers.plan.model.dao.TravelPlannerMapper;
import com.kh.avengers.plan.model.dto.TravelPlannerDto;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep4Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep4Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TravelPlannerStep4ServiceImpl implements TravelPlannerStep4Service {

    private final TravelPlannerMapper travelPlannerMapper;
    
     /**
     * step4 플랜 업데이트 및 완료
     * @param request step4에서 입력받은 플랜 완성 정보
     * @param userDetails 인증된 사용자의 정보
     * @return
     */
    @Override
    @Transactional
    public TravelPlannerStep4Response completeStep4Plan(TravelPlannerStep4Request request, CustomUserDetails userDetails){

        log.info("여행플랜 step4 완료 처리 시작 >> 플랜 번호 : {}, 사용자 : {}, 제목 : {}", request.getPlanNo(), userDetails.getMemberName(), request.getPlanTitle());

        // 1. 플랜 번호로 기존 여행의 플랜을 조회
        TravelPlannerDto existingPlan = travelPlannerMapper.selectTravelPlanByPlanNo(request.getPlanNo());

        // 2. 플랜이 존재하지 않는 경우 예외처리
        if(existingPlan == null){
            log.error("존재하지 않는 플랜 번호 : {} >> ", request.getPlanNo());
            throw new InvalidException("해당 플랜을 찾을 수 없습니다.");
        }

        // 3. 플랜의 소유자 확인(>> 본인의 플랜인지 확인)
        if(!existingPlan.getMemberNo().equals(userDetails.getMemberNo())){
            log.error("플랜 소유자 불일치 >> 플랜 소유자 : {}, 요청자 : {}", existingPlan.getMemberNo(), userDetails.getMemberNo());
            throw new ForbiddenException("해당 플랜에 대한 접근 권한이 없습니다.");
        }

        // 4. 예산 검증 >> 최소 예산이 최대 예산보다 크면 안됨
        if(request.getMinBudget() > request.getMaxBudget()){
            log.error("예산 설정 오류 >> 최소 : {}, 최대 : {}", request.getMinBudget(), request.getMaxBudget());
            throw new InvalidException("최대 예산은 최소 에산보다 커야합니다.");
        }

        // 5. 업데이트할 데이터 준비
        TravelPlannerDto updateDto = TravelPlannerDto.builder()
                                                     .planNo(request.getPlanNo())
                                                     .planTitle(request.getPlanTitle())
                                                     .planDescription(request.getPlanDescription())
                                                     .minBudget(request.getMinBudget())
                                                     .maxBudget(request.getMaxBudget())
                                                     .transportReservationLink(request.getFlightLink())
                                                     .accommodationLink(request.getHotelLink())
                                                     .build();


        // 6. DB에 step4 정보 업데이트
        int updateResult = travelPlannerMapper.updateTravelPlanStep4(updateDto);

        // 7. 결과 확인
        if(updateResult != 1){
            log.error("여행플랜 step4 업데이트 실패!!!! >> 플랜번호 : {}", request.getPlanNo());
            throw new InvalidException("여행플랜 완료를 하지 못하였습니다.");
        }

        log.info("step4 여행 플랜 생성 완료~~ >> 플랜번호 : {}, 제목 : {}", request.getPlanNo(), request.getPlanTitle());

        // 8. 응답 DTO 생성
        return TravelPlannerStep4Response.builder()
        .planNo(request.getPlanNo())
        .planTitle(request.getPlanTitle())
        .planDescription(request.getPlanDescription())
        .minBudget(request.getMinBudget())
        .maxBudget(request.getMaxBudget())
        .flightLink(request.getFlightLink())
        .hotelLink(request.getHotelLink())
        .message("여행 플랜이 성공적으로 완료되었습니다.")
        .build();
    }
}
