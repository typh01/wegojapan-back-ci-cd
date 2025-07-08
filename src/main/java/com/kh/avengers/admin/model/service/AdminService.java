package com.kh.avengers.admin.model.service;

import com.kh.avengers.common.dto.RequestData;

public interface AdminService {

  RequestData selectMembers(int page, String status, String role);


  RequestData updateMemberRole(Long memberNo, String role);

  RequestData reportMember(int page);


  RequestData updateReportStatus(Long reportNo);



}
