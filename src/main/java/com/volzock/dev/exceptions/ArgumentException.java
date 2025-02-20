package com.volzock.dev.exceptions;

public class ArgumentException extends Error {

    public static final String EMPTY_ARGUMENTS = "No arguments given";
    public static final String UNKNOWN_ARGUMENT = "%s is unknown argument";
    public static final String NOT_VALID_TYPE = "%s is not a valid argument, valid types: %s";

    public ArgumentException(String message) {
        super(message);
    }
}
