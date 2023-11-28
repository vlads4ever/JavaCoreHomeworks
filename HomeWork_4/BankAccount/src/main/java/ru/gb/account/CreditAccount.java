package ru.gb.account;

/**
 * Класс Кредитного счета c уже имеющимся балансом в 10 тыс.
 */
public class CreditAccount extends Account {
    public CreditAccount(String name, String accountNumber) {
        super(name, accountNumber, 10000);
    }

}
