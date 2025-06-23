package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.NotFoundException;

public class EmailNotFoundException extends NotFoundException{
  public EmailNotFoundException(String message){
    super(message);
  }

}
