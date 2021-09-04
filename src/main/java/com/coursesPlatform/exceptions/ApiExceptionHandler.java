package com.coursesPlatform.exceptions;

import com.coursesPlatform.coursePortfolio.CustomerNotFoundException;
import com.coursesPlatform.coursePortfolio.MailIsAlreadyInUseException;
import com.coursesPlatform.coursePortfolio.MailIsInvalidException;
import com.coursesPlatform.coursePortfolio.TrainerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {CategoryNotFoundException.class, CourseNotFoundException.class, TrainerNotFoundException.class, CustomerNotFoundException.class})
    ResponseEntity<String> handleNotFoundExceptions(Exception e) {
        return new ResponseEntity<String>("Not Found!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalLengthException.class)
    ResponseEntity<String> handleIllegalLengthException(Exception e) {
        return new ResponseEntity<String>("Illegal length !" ,  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MailIsAlreadyInUseException.class)
    ResponseEntity<String> handleEmptyListException(Exception e) {
        return new ResponseEntity<String>("Mail is already used!", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MailIsInvalidException.class)
    ResponseEntity<String> handleInvalidMailException(Exception e){
        return new ResponseEntity<String>("Mail is invalid!",HttpStatus.BAD_REQUEST);
    }
}
