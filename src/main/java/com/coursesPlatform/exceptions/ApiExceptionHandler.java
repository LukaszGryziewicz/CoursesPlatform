package com.coursesPlatform.exceptions;

import com.coursesPlatform.coursePortfolio.CustomerNotFoundException;
import com.coursesPlatform.coursePortfolio.MailInvalidException;
import com.coursesPlatform.coursePortfolio.MailIsAlreadyInUseException;
import com.coursesPlatform.trainer.TrainerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {CategoryNotFoundException.class, CourseNotFoundException.class, TrainerNotFoundException.class, CustomerNotFoundException.class})
    ResponseEntity<String> handleNotFoundExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {IllegalTitleLengthException.class, IllegalDescriptionLengthException.class})
    ResponseEntity<String> handleIllegalLengthException(Exception e) {
        return new ResponseEntity<>(e.getMessage(),  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MailIsAlreadyInUseException.class)
    ResponseEntity<String> handleEmptyListException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MailInvalidException.class)
    ResponseEntity<String> handleInvalidMailException(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CategoryTitleAlreadyExists.class, CourseTitleAlreadyExists.class, LectureTitleAlreadyExists.class})
    ResponseEntity<String> handleAlreadyTitleTakenException(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
