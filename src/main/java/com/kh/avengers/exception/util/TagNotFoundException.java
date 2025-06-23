package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.NotFoundException;

public class TagNotFoundException extends NotFoundException{

  public TagNotFoundException(String message){
    super(message);
  }

}
