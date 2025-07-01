package com.kh.avengers.member.model.service;


import java.util.Map;


import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.member.model.dto.ChangeMemberNameDTO;
import com.kh.avengers.member.model.dto.MemberDTO;





public interface MemberService {

  RequestData signUp(MemberDTO member);

  RequestData updateMember(ChangeMemberNameDTO member);

  RequestData updatePassword(Map<String, String> member);

  RequestData deleteMember(Map<String, String> member);



 

}
