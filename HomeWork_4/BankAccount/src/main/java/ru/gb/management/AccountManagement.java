package ru.gb.management;

import ru.gb.account.Account;
import ru.gb.account.CommonAccount;
import ru.gb.exceptions.InsufficientFundsException;
import ru.gb.exceptions.WrongAccountNumberException;
import ru.gb.validations.Validation;

import java.util.ArrayList;
import java.util.List;

public class AccountManagement {
    private List<Account> commonAccountsList;

    public AccountManagement() {
        this.commonAccountsList = new ArrayList<>();
    }

    /**
     * Метод создает новый счет, если верны параметры
     * @param name Имя владельца счета
     * @param accountNumber Номер счета
     * @param startBalance Баланс средств на счету
     * @throws WrongAccountNumberException Неверный формат счета
     * @throws IllegalArgumentException Отрицательная сумма
     */
    public void createCommonAccount(String name, String accountNumber, double startBalance)
            throws WrongAccountNumberException, IllegalArgumentException {

        if (!Validation.checkAccountNumber(accountNumber))
            throw new WrongAccountNumberException("Неверный формат счета. Должно быть только 10 цифр.");

        if (!Validation.checkSum(startBalance))
            throw new IllegalArgumentException("Не может быть отрицательной суммы!");

        commonAccountsList.add(new CommonAccount(name, accountNumber, startBalance));
        System.out.printf("Счет на пользователя %s создан.\n", name);
    }

    /**
     * Метод осуществляет начисление средств на счет
     * @param accountNumber Номер счета
     * @param sum Сумма пополненния
     * @throws IllegalArgumentException Отрицательная сумма
     */
    public void putToAccount(String accountNumber, double sum) throws IllegalArgumentException {
        Account account = getAccountByAccountNumber(accountNumber);
        if (account != null) {
            if (Validation.checkSum(sum)) {
                account.putToAccount(sum);
                System.out.printf("Счет %s был пополнен на сумму: %.2f. Баланс счета: %.2f.\n",
                        account.getAccountNumber(), sum, account.getBalance());
            } else {
                throw new IllegalArgumentException("Не может быть отрицательной суммы!");
            }
        } else {
            System.out.printf("Счет %s не найден.\n", accountNumber);
        }
    }

    /**
     * Метод осуществляет снятие средств со счета
     * @param accountNumber Номер счета
     * @param sum Сумма пополненния
     * @throws InsufficientFundsException Недостаточно средств на балансе
     * @throws IllegalArgumentException Отрицательная сумма
     */
    public void getFromAccount(String accountNumber, double sum)
            throws InsufficientFundsException, IllegalArgumentException {
        Account account = getAccountByAccountNumber(accountNumber);
        if (account != null) {
            if (Validation.checkSum(sum)) {
                if (!Validation.checkBalance(account, sum))
                    throw new InsufficientFundsException("На балансе счета недостаточно средств!");
                account.getFromAccount(sum);
                System.out.printf("Со счета %s снята сумма: %.2f. Баланс счета: %.2f.\n",
                        accountNumber, sum, account.getBalance());
            } else {
                throw new IllegalArgumentException("Не может быть отрицательной суммы!");
            }
        } else {
            System.out.printf("Счет %s не найден.\n", accountNumber);
        }
    }

    private Account getAccountByAccountNumber(String accountNumber) {
        for (Account account: commonAccountsList) {
            if (account.getAccountNumber() == accountNumber)
                return account;
        }
        return null;
    }

}
