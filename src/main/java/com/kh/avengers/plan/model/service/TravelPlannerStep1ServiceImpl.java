package com.kh.avengers.plan.model.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.avengers.auth.model.vo.CustomUserDetails;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.exception.util.InvalidTravelDateException;
import com.kh.avengers.plan.model.dao.TravelPlannerMapper;
import com.kh.avengers.plan.model.dto.TravelPlannerDto;
import com.kh.avengers.plan.model.dto.request.TravelPlannerStep1Request;
import com.kh.avengers.plan.model.dto.response.TravelPlannerStep1Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TravelPlannerStep1ServiceImpl implements TravelPlannerStep1Service {

    private final TravelPlannerMapper travelPlannerMapper;

    /**
     * step1 플랜 생성 & 저장
     * @param request step1에서 입력받은 여행플랜의 기본 종보
     * @param userDetails 인증된 사용자 정보
     * @return
     */
    @Override
    @Transactional
    public TravelPlannerStep1Response createStep1Plan(TravelPlannerStep1Request request, CustomUserDetails userDetails){

        log.info("여행플랜 step1 생성 시작 - 사용자 번호 : {}, 사용자명 : {}", userDetails.getMemberNo(), userDetails.getMemberName());

        // 1. 유효성 검사
        validateStep1Request(request);

        // 2. 요청 데이터를 DB 저장용 DTO로 변환
        TravelPlannerDto travelPlannerDto = convertToDto(request, userDetails);

        // 3. DB에 여행 플랜 저장
        int insertResult = travelPlannerMapper.insertTravelPlan(travelPlannerDto);

        // 4. 삽입 결과 확인
        if(insertResult != 1){
            log.error("여행플랜 저장 실패!!");
            throw new InvalidException("여행플랜 저장에 실패했습니다.");
        }

        // 5. 생성된 플랜 번호 확인
        Long generatedPlanNo = travelPlannerDto.getPlanNo();
        if(generatedPlanNo == null || generatedPlanNo <= 0) {
            log.error("플랜 번호 생성 실패!!");
            throw new InvalidException("여행플랜 번호 생성에 실패했습니다.");
        }

        // 6. 성공 로그 찍기
        log.info("step1 여행 플랜 생성 완료!!! >> 플랜번호 : {}, 사용자 번호 : {}", generatedPlanNo, userDetails.getMemberNo());

        // 7. 응답 DTO 생성
        return TravelPlannerStep1Response.builder()
                .planNo(generatedPlanNo)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .travelers(request.getTravelers())
                .build();

    }

    /**
     * step1 플랜 요청 데이터 유효성 검사
     * @param request 검사할 step1의 요청 데이터
     */
    public void validateStep1Request(TravelPlannerStep1Request request){
        LocalDate currentDate = LocalDate.now();

        // 여행 시작일이 현재 날짜 이후인지
        if(request.getStartDate().isBefore(currentDate)){
            log.warn("유효하지 않은 여행 시작일입니다. >> 입력일 : {}, 현재일 : {}", request.getStartDate(), currentDate);
            throw new InvalidTravelDateException("여행 시작일은 오늘 이후로 선택헤주세요.");
        }

        // 여행 종료일이 오늘 이후인지 확인
        if(request.getEndDate().isBefore(request.getStartDate())){
            log.warn("유효하지 않은 여행 종료일입니다. >> 시작일 : {}, 종료일 : {}", request.getStartDate(), request.getEndDate());
            throw new InvalidTravelDateException("여행 종료일은 시작일보다 늦어야 합니다.");
        }

    }

    public TravelPlannerDto convertToDto(TravelPlannerStep1Request request, CustomUserDetails userDetails){
        return TravelPlannerDto.builder()
                .memberNo(userDetails.getMemberNo())
                .travelStartDate(request.getStartDate())
                .travelEndDate(request.getEndDate())
                .groupSize(request.getTravelers())
                .planTitle(null)
                .planDescription(null)
                .build();

    }
    
}
