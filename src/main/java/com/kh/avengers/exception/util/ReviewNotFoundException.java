package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.NotFoundException;

public class ReviewNotFoundException extends NotFoundException{

  public ReviewNotFoundException(String message){
    super(message);
  }

}
