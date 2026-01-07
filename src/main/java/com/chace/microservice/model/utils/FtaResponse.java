package com.chace.microservice.model.utils;

import lombok.Builder;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter @Setter @Builder
public class FtaResponse<T> {
  protected ZonedDateTime timeStamp;
  private   HttpStatus    status;
  private   String        message;
  private   T             data;
  private   Object     error;
  private   String        errorCode;
  private   Integer       total;
  private   Integer       page;
}
