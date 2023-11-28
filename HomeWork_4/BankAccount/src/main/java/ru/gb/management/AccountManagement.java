package ru.gb.management;

import ru.gb.account.Account;
import ru.gb.account.CommonAccount;
import ru.gb.exceptions.WrongAccountNumberException;
import ru.gb.validations.Validation;

import java.util.ArrayList;
import java.util.List;

public class AccountManagement {
    private List<Account> commonAccountsList;


    public AccountManagement() {
        this.commonAccountsList = new ArrayList<>();
    }

    public void createCommonAccount(String name, String accountNumber, double startBalance)
            throws WrongAccountNumberException, IllegalArgumentException {

        if (!Validation.checkAccountNumber(accountNumber))
            throw new WrongAccountNumberException("Неверный формат счета. Должно быть только 10 цифр.");

        if (!Validation.checkSum(startBalance))
            throw new IllegalArgumentException("Не может быть отрицательной суммы!");

        commonAccountsList.add(new CommonAccount(name, accountNumber, startBalance));
        System.out.printf("Счет на пользователя %s создан.\n", name);
    }

    public void putToAccount(String accountNumber, double sum) throws IllegalArgumentException {
        Account account = getAccountByAccountNumber(accountNumber);
        if (account != null) {
            if (Validation.checkSum(sum)) {
                account.putToAccount(sum);
                System.out.printf("Счет был пополнен на сумму: %.2f. Сумма на счете: %.2f.\n",
                        sum, account.getBalance());
            } else {
                throw new IllegalArgumentException("Не может быть отрицательной суммы!");
            }
        } else {
            System.out.printf("Счет %s не найден.\n", accountNumber);
        }
    }

    public void getFromAccount(String accountNumber) {

    }

    private Account getAccountByAccountNumber(String accountNumber) {
        for (Account account: commonAccountsList) {
            if (account.getAccountNumber() == accountNumber)
                return account;
        }
        return null;
    }

}
