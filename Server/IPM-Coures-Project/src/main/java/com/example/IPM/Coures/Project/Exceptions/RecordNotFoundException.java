package com.example.IPM.Coures.Project.Exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException() {
        super("record has not founded");
    }
}
