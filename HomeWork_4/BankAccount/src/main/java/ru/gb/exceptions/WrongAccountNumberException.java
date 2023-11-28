package ru.gb.exceptions;

public class WrongAccountNumberException extends Exception {
    public WrongAccountNumberException(String message) {
        super(message);
    }
}
