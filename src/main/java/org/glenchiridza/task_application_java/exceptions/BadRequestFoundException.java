package org.glenchiridza.task_application_java.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestFoundException extends RuntimeException{
    public BadRequestFoundException(String message){
        super(message);
    }
}
