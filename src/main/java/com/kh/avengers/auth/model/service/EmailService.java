package com.kh.avengers.auth.model.service;

import java.util.Map;

import com.kh.avengers.auth.model.dto.EmailDTO;
import com.kh.avengers.auth.model.dto.FindIdRequestDTO;
import com.kh.avengers.common.dto.RequestData;







public interface EmailService {

  RequestData signUpEmailCode(Map<String, String> email);

  RequestData checkVerifyCode(EmailDTO code);

  RequestData findId(FindIdRequestDTO findId);

  RequestData findVerifyCode(EmailDTO findCode);

  RequestData findPassword(Map<String, String> findPw);

  RequestData findPasswordCode(Map<String, String> findPwCode);

  RequestData newPassword(Map<String, String> newPw);


}
