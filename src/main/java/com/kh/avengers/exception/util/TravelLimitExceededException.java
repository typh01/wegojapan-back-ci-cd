package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.InvalidException;

public class TravelLimitExceededException extends InvalidException{
  public TravelLimitExceededException(String message){
    super(message);
  }

}
