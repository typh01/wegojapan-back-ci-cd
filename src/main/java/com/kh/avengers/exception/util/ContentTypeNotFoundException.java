package com.kh.avengers.exception.util;

import com.kh.avengers.exception.commonexception.NotFoundException;

public class ContentTypeNotFoundException extends NotFoundException{
  public ContentTypeNotFoundException(String message){
    super(message);
  }

}
