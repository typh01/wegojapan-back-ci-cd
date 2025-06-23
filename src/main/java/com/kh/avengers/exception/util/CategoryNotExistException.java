package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.NotExistException;

public class CategoryNotExistException extends NotExistException{
  public CategoryNotExistException(String message){
    super(message);
  }

}
