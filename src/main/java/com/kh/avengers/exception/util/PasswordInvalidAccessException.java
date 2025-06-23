package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.InvalidException;

public class PasswordInvalidAccessException extends InvalidException{

  public PasswordInvalidAccessException(String message){
    super(message);
  }

}
