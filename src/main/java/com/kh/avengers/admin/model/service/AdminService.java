package com.kh.avengers.admin.model.service;

import com.kh.avengers.common.dto.RequestData;

public interface AdminService {

  RequestData selectMembers(int page, String status, String role);

  RequestData updateMemberStatus(Long memberNo, String status);

  RequestData updateMemberRole(Long memberNo, String role);

}
