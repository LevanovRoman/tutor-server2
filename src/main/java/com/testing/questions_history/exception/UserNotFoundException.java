package com.testing.questions_history.exception;

import java.security.cert.Extension;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message){
        super(message);
    }
}
