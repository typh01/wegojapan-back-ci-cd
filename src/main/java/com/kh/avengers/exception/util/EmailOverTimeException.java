package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.InvalidException;

public class EmailOverTimeException extends InvalidException{
  public EmailOverTimeException(String message){
    super(message);
  }

}
