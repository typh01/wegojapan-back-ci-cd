package com.kh.avengers.admin.model.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.avengers.admin.model.dto.AdminMemberDTO;
import com.kh.avengers.admin.model.dto.AdminReviewReportDTO;
import com.kh.avengers.reviews.model.dto.ReviewDTO;


@Mapper
public interface AdminMapper {

  List<AdminMemberDTO> findAllMembers(RowBounds pages);


  
  AdminMemberDTO getMemberById(Long memberNo);

  void updateMemberRole(Long memberNo, String role);

  List<AdminReviewReportDTO> selectReportMembers(RowBounds rowBounds);

  Long countAllMember();

  Long countReportMembers();



  Long updateStatusReport(Long reportNo);



  ReviewDTO findReviewByNo(Long reviewNo);

}
