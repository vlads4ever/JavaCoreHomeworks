package ru.gb.exceptions;

/**
 * Исключение неверного формата номера счета
 */
public class WrongAccountNumberException extends Exception {
    public WrongAccountNumberException(String message) {
        super(message);
    }
}
