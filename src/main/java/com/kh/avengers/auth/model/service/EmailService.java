package com.kh.avengers.auth.model.service;

import java.util.Map;

import com.kh.avengers.auth.model.dto.EmailDTO;
import com.kh.avengers.common.dto.RequestData;

import jakarta.validation.Valid;


public interface EmailService {

  RequestData signUpEmailCode(Map<String, String> email);

  RequestData checkVerifyCode(EmailDTO code);

}
