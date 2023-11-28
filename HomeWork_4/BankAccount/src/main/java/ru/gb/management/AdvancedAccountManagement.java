package ru.gb.management;

import ru.gb.account.Account;
import ru.gb.account.CreditAccount;
import ru.gb.account.DebitAccount;
import ru.gb.exceptions.InsufficientFundsException;
import ru.gb.exceptions.WrongAccountNumberException;
import ru.gb.validations.Validation;

import java.util.ArrayList;
import java.util.List;

public class AdvancedAccountManagement {
    private List<Account> advancedAccountsList;

    public AdvancedAccountManagement() {
        this.advancedAccountsList = new ArrayList<>();
    }

    public void createCreditAccount(String name, String accountNumber) throws WrongAccountNumberException {
        if (!Validation.checkAccountNumber(accountNumber))
            throw new WrongAccountNumberException("Неверный формат счета. Должно быть только 10 цифр.");

        advancedAccountsList.add(new CreditAccount(name, accountNumber));
        System.out.printf("Кредитный счет на пользователя %s создан.\n", name);
    }

    public void createDebitAccount(String name, String accountNumber, double startBalance)
            throws WrongAccountNumberException, IllegalArgumentException {

        if (!Validation.checkAccountNumber(accountNumber))
            throw new WrongAccountNumberException("Неверный формат счета. Должно быть только 10 цифр.");

        if (!Validation.checkSum(startBalance))
            throw new IllegalArgumentException("Не может быть отрицательной суммы!");

        advancedAccountsList.add(new DebitAccount(name, accountNumber, startBalance));
        System.out.printf("Дебитовый счет на пользователя %s создан.\n", name);
    }

    public void doTransaction(String fromAccountNumber, String toAccountNumber, double sum)
            throws InsufficientFundsException, IllegalArgumentException {
        Account fromAccount = getAccountByAccountNumber(fromAccountNumber);
        Account toAccount = getAccountByAccountNumber(fromAccountNumber);
        if (fromAccount != null) {
            if (toAccount != null) {
                Transaction.moneyTransfer(fromAccount, toAccount, sum);
            } else System.out.printf("Счет %s не найден.\n", toAccount);
        } else System.out.printf("Счет %s не найден.\n", fromAccount);
    }

    private Account getAccountByAccountNumber(String accountNumber) {
        for (Account account: advancedAccountsList) {
            if (account.getAccountNumber() == accountNumber)
                return account;
        }
        return null;
    }
}
