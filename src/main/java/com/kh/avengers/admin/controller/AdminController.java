package com.kh.avengers.admin.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.avengers.admin.model.service.AdminService;
import com.kh.avengers.common.dto.RequestData;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

  private final AdminService adminService;

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @GetMapping("members")
  public ResponseEntity<RequestData> selectMembers(@RequestParam(name = "page", defaultValue = "1") int page, 
                                                   @RequestParam(name="status", required=false) String status, 
			                                             @RequestParam(name="role", required=false) String role){

    RequestData result = adminService.selectMembers(page, status, role);
    return ResponseEntity.ok(result);
  }



  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PutMapping("/{memberNo}/role")
  public ResponseEntity<RequestData> updateMemberRole(@PathVariable("memberNo") Long memberNo,
                                                      @RequestParam(name="role") String role){
  
    RequestData result = adminService.updateMemberRole(memberNo, role);
    return ResponseEntity.ok(result);
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @GetMapping("/reportList")
  public ResponseEntity<RequestData> reportMember(@RequestParam(name = "page", defaultValue = "1") int page){

    RequestData result = adminService.reportMember(page);

    return ResponseEntity.ok(result);
  
  }


}
