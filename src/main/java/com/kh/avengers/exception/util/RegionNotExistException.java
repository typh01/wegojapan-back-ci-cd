package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.NotExistException;

public class RegionNotExistException extends NotExistException{
  public RegionNotExistException(String message){
    super(message);
  }

}
