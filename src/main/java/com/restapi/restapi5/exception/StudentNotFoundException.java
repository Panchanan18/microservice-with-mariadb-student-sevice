package com.restapi.restapi5.exception;

public class StudentNotFoundException extends Exception{
    public StudentNotFoundException() {
        super();
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
