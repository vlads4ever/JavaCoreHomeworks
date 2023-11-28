package ru.gb.exceptions;

/**
 * Исключение недостатка средств на балансе
 */
public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message) {
        super(message);
    }
}
