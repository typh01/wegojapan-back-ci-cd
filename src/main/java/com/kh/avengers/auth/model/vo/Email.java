package com.kh.avengers.auth.model.vo;

import java.util.Date;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Email {

    private String email;
    private String verifyCode;

    private Date createDate;

}
