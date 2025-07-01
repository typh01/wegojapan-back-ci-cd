package com.kh.avengers.plan.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep4Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep4Response;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class TravelPlannerStep4ServiceImpl implements TravelPlannerStep4Service {
    
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


        // 2. 플랜이 존재하지 않는 경우 예외처리


        // 3. 플랜의 소유자 확인(>> 본인의 플랜인지 확인)


        // 4. 예산 검증 >> 최소 예산이 최대 예산보다 크면 안됨


        // 5. 업데이트할 데이터 준비


        // 6. DB에 step4 정보 업데이트


        // 7. 결과 확인


        // 8. 응답 DTO 생성


        return null;
    }
}
