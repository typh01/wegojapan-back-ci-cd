package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.NotFoundException;

public class ResourceNotFoundException extends NotFoundException{
  public ResourceNotFoundException(String message){
    super(message);
    
  }

}
