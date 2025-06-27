package com.kh.avengers.auth.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.avengers.auth.model.dto.EmailDTO;

@Mapper
public interface EmailMapper {

  void signUpEmailCode(String email);

  void sendCodeEmail(EmailDTO emailInfo);

  EmailDTO checkedVerifyCode(EmailDTO Code);

}
