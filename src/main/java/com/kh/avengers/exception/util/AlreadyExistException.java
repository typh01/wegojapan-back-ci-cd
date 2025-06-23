package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.InvalidException;

public class AlreadyExistException extends InvalidException{
  public AlreadyExistException(String message){
    super(message);
  }

}
