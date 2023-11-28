package ru.gb.account;

public abstract class Account {
    private final String name;
    private final String accountNumber;
    private double balance;

    public Account(String name, String accountNumber, double startBalance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = startBalance;
    }

    public String putToAccount(double sum) {

        return "Операция выполнена";
    }

    public String getFromAccount() {
        return "Операция выполнена";

    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}
