package com.kh.avengers.util;

import org.springframework.stereotype.Component;

import com.kh.avengers.common.dto.RequestData;

@Component
public class ResponseUtil {

  public RequestData rd(String code, Object data, String message){

    return RequestData.builder()
                       .code(code)
                       .data(data)
                       .message(message)
                       .build();
   
  }
}
