package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.NotExistException;

public class TagNotExistException extends NotExistException{
  public TagNotExistException(String message){
    super(message);
  }

}
