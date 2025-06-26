package com.kh.avengers.exception;

import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.exception.commonexception.DeleteException;
import com.kh.avengers.exception.commonexception.InvalidException;
import com.kh.avengers.exception.commonexception.NotExistException;
import com.kh.avengers.exception.commonexception.NotFoundException;
import com.kh.avengers.exception.commonexception.UpdateException;
import com.kh.avengers.exception.util.AlreadyExistException;
import com.kh.avengers.exception.util.CustomMessagingException;
import com.kh.avengers.exception.util.ForbiddenException;
import com.kh.avengers.exception.util.TagAddException;
import com.kh.avengers.exception.util.TravelSyncFailException;
import com.kh.avengers.exception.util.UnAuthorizedException;
import com.kh.avengers.util.ErrorResponseUtil;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final ErrorResponseUtil errorResponseUtil;

  private ResponseEntity<RequestData> makeResponseEntity(RuntimeException e, HttpStatus status){
    RequestData rd = errorResponseUtil.rd("400", e.getMessage());
    return ResponseEntity.status(status).body(rd);
    
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<RequestData> handleNotFoundException(NotFoundException e){
    return makeResponseEntity(e, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NotExistException.class)
  public ResponseEntity<RequestData> handleNotExistException(NotExistException e){
    return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UpdateException.class)
  public ResponseEntity<RequestData> handleUpdateException(UpdateException e){
    return makeResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler(DeleteException.class)
  public ResponseEntity<RequestData> handleDeleteException(DeleteException e){
    return makeResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AlreadyExistException.class)
  public ResponseEntity<RequestData> handleAleadyExistException(AlreadyExistException e){
    return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(TagAddException.class)
  public ResponseEntity<RequestData> handleTagAddException(TagAddException e){
    return makeResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(TravelSyncFailException.class)
  public ResponseEntity<RequestData> handleTravelSyncFailException(TravelSyncFailException e){
    return makeResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler(UnAuthorizedException.class)
  public ResponseEntity<RequestData> handleUnAuthorizedException(UnAuthorizedException e){
     return makeResponseEntity(e, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<RequestData> handleForbiddenException(ForbiddenException e){
    return makeResponseEntity(e, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(InvalidException.class)
  public ResponseEntity<RequestData> handleInvalidException(InvalidException e){
    return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CustomMessagingException.class)
  public ResponseEntity<RequestData> handleCustomMessagingException(CustomMessagingException e){
    return makeResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
