package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.NotFoundException;

public class RegionNotFoundException extends NotFoundException{
  public RegionNotFoundException(String message){
    super(message);
  }

}
