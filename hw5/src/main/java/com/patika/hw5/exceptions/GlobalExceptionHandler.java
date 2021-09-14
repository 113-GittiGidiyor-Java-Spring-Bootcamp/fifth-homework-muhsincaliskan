package com.patika.hw5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({CourseIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SchoolAppErrorResponse> handleException(CourseIsAlreadyExistException exc){
        SchoolAppErrorResponse response= prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({InstructorIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SchoolAppErrorResponse> handleException(InstructorIsAlreadyExistException exc){
        SchoolAppErrorResponse response= prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({StudentAgeNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SchoolAppErrorResponse> handleException(StudentAgeNotValidException exc){
        SchoolAppErrorResponse response= prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({StudentNumberForOneCourseExceededException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SchoolAppErrorResponse> handleException(StudentNumberForOneCourseExceededException exc){
        SchoolAppErrorResponse response= prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    private SchoolAppErrorResponse prepareErrorResponse(HttpStatus httpStatus, String message) {
        SchoolAppErrorResponse response = new SchoolAppErrorResponse();
        response.setStatus(httpStatus.value());
        response.setMessage(message);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }
}
