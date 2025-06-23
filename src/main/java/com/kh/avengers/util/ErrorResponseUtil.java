package com.kh.avengers.util;

import org.springframework.stereotype.Component;

import com.kh.avengers.common.dto.RequestData;

@Component
public class ErrorResponseUtil {

    public RequestData rd(String code, String message){

    return RequestData.builder()
                       .code(code)
                       .message(message)
                       .build();
   
  }
}
