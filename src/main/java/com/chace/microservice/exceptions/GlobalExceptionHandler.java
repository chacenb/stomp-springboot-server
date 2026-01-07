package com.chace.microservice.exceptions;

import com.chace.microservice.model.utils.FtaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override /* default handler for ALL EXCEPTIONS */
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
    log.error("____________________[Exception internal] >> ", ex);
    return ResponseEntity.badRequest().body(FtaResponse.builder()
                                                          .timeStamp(ZonedDateTime.now())
                                                          .status(HttpStatus.valueOf(statusCode.value()))
                                                          .message(ex.getMessage())
                                                          .error(ex.getMessage())
                                                          .build());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    log.error("____________________[Method Argument Not Valid] >>", exception);

    /* get all the errors messages for clean returning */
    List<String> errorList = exception.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

    return ResponseEntity.badRequest().body(FtaResponse.builder()
                                                          .timeStamp(ZonedDateTime.now())
                                                          .status(HttpStatus.valueOf(status.value()))
                                                          .message(errorList.toString())
                                                          .build());
  }


  @ExceptionHandler({CustomException.class,}) /* handler for FTA's CustomException */
  public ResponseEntity<FtaResponse<?>> customExceptionHandler(CustomException ex) {
    log.error("____________________[FTA custom Exception] >> code = {}", ex.getCode(), ex);
    return ResponseEntity.badRequest().body(FtaResponse.builder()
                                                       .timeStamp(ZonedDateTime.now())
                                                       .status(HttpStatus.BAD_REQUEST)
                                                       .message(ex.getMessage())
//                                                       .error(ex)
                                                       .errorCode(ex.getCode().getLabel())
                                                       .build());
  }

}
