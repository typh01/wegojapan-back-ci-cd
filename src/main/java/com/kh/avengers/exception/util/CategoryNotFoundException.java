package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.NotFoundException;

public class CategoryNotFoundException extends NotFoundException{
  public CategoryNotFoundException(String message){
    super(message);
  }

}
