package com.chace.microservice.model.utils;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class FtaExceptionHandler extends ResponseEntityExceptionHandler {

    /* this exception handler will handle params annotated with @Valid in REST Controllers and validate them */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request) {

        /* get all the errors messages in a clean format to return */
        List<String> errorList = exception.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        log.error("Method Arguments are Not Valid :: \nerrors = {},\n headers = {},\n status = {},\n request = {}", errorList, headers, status, request);

        return ResponseEntity.badRequest().body(
                FtaResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(errorList.toString())
                        .build()
        );
    }


    /* We can override many other methods coming from "ResponseEntityExceptionHandler" to define a custom bihavior if needed */

}
