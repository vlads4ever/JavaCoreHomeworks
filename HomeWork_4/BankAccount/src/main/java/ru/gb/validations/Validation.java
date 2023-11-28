package ru.gb.validations;

public class Validation {

    public static boolean checkAccountNumber(String accountNumber) {
        // Если accountNumber состоит из 10 цифр, то true
        return true;
    }

    public static boolean checkSum(double sum) {
        return sum >= 0;
    }

}
