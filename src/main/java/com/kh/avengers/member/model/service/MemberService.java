package com.kh.avengers.member.model.service;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.member.model.dto.MemberDTO;

public interface MemberService {

  RequestData signUp(MemberDTO member);

}
