package com.stanislav.utils.exceptions;

/**
 * @author Stanislav Hlova
 */
public class SensorNotRegisteredException extends RuntimeException{
    public SensorNotRegisteredException(String message) {
        super(message);
    }
}
