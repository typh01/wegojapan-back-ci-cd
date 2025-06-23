package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.NotExistException;

public class ContentTypeNotExistException extends NotExistException{
  public ContentTypeNotExistException(String message){
    super(message);
  }

}
