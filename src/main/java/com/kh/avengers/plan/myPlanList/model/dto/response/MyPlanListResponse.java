package com.kh.avengers.plan.myPlanList.model.dto.response;

import com.kh.avengers.plan.myPlanList.model.dto.MyPlanListDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPlanListResponse {

   private List<MyPlanListDto> plans;
   private Integer currentPage;
   private Integer totalPages;
   private Long totalElements; // 전체 항목 수
   private Integer pageSize;
   private  Boolean hasPrevious;
   private  Boolean hasNext;

}
