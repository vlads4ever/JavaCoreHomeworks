package ru.gb.account;

/**
 * Класс Дебетового счета без стартового баланса
 */
public class DebitAccount extends Account {
    public DebitAccount(String name, String accountNumber, double startBalance) {
        super(name, accountNumber, startBalance);
    }
}
