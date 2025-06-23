package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.InvalidException;

public class EmailSameException extends InvalidException{
  public EmailSameException(String message){
    super(message);
  }

}
