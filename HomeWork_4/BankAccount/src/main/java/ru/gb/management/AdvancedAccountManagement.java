package ru.gb.management;

import ru.gb.account.Account;
import ru.gb.account.CreditAccount;
import ru.gb.account.DebitAccount;
import ru.gb.exceptions.InsufficientFundsException;
import ru.gb.exceptions.WrongAccountNumberException;
import ru.gb.validations.Validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранит и управляет счетами клиентов
 */
public class AdvancedAccountManagement {
    private List<Account> advancedAccountsList;

    public AdvancedAccountManagement() {
        this.advancedAccountsList = new ArrayList<>();
    }

    /**
     * Класс создает Кредитный счет с определенным лимитом
     * @param name Имя клиента
     * @param accountNumber Номер счета клиента
     * @param limit Кредитный лимит
     * @throws WrongAccountNumberException
     */
    public void createCreditAccount(String name, String accountNumber, double limit)
            throws WrongAccountNumberException {
        if (!Validation.checkAccountNumber(accountNumber))
            throw new WrongAccountNumberException("Неверный формат счета. Должно быть только 10 цифр.");

        advancedAccountsList.add(new CreditAccount(name, accountNumber, limit));
        System.out.printf("Кредитный счет на пользователя %s создан с лимитом в %.2f.\n", name, limit);
    }

    /**
     * Класс создает Дебитовый счет с начальным балансом
     * @param name Имя клиента
     * @param accountNumber Номер счета клиента
     * @param startBalance Начальный баланс
     * @throws WrongAccountNumberException
     * @throws IllegalArgumentException
     */
    public void createDebitAccount(String name, String accountNumber, double startBalance)
            throws WrongAccountNumberException, IllegalArgumentException {

        if (!Validation.checkAccountNumber(accountNumber))
            throw new WrongAccountNumberException(
                    String.format("Неверный формат счета. Вы ввели: %s. Должно быть только 10 цифр.", accountNumber));

        if (!Validation.checkSum(startBalance))
            throw new IllegalArgumentException(
                    String.format("Не может быть отрицательной суммы! Указанная сумма: %.2f", startBalance));

        advancedAccountsList.add(new DebitAccount(name, accountNumber, startBalance));
        System.out.printf("Дебитовый счет на пользователя %s создан c начальным балансом %.2f.\n", name, startBalance);
    }

    public void doTransaction(String fromAccountNumber, String toAccountNumber, double sum)
            throws InsufficientFundsException, IllegalArgumentException {
        Account fromAccount = getAccountByAccountNumber(fromAccountNumber);
        Account toAccount = getAccountByAccountNumber(toAccountNumber);
        if (fromAccount != null) {
            if (toAccount != null) {
                Transaction.moneyTransfer(fromAccount, toAccount, sum);
                Transaction.printBalance(fromAccount);
                Transaction.printBalance(toAccount);
            } else System.out.printf("Счет %s не найден.\n", toAccount);
        } else System.out.printf("Счет %s не найден.\n", fromAccount);
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
            if (account instanceof CreditAccount){
                putToCreditAccount((CreditAccount) account, sum);
            } else {
                putToDebitAccount((DebitAccount) account, sum);
            }
        } else {
            System.out.printf("Счет %s не найден.\n", accountNumber);
        }
    }

    private void putToCreditAccount(CreditAccount account, double sum) throws IllegalArgumentException {
        if (Validation.checkSum(sum)) {
            account.putToAccount(sum);
            System.out.printf("Счет %s был пополнен на сумму: %.2f.\n",
                    account.getAccountNumber(), sum);
            Transaction.printBalance(account);
        } else {
            throw new IllegalArgumentException(
                    String.format("Не может быть отрицательной суммы! Указанная сумма: %.2f", sum));
        }
    }

    private void putToDebitAccount(DebitAccount account, double sum) throws IllegalArgumentException {
        if (Validation.checkSum(sum)) {
            account.putToAccount(sum);
            System.out.printf("Счет %s был пополнен на сумму: %.2f.\n",
                    account.getAccountNumber(), sum);
            Transaction.printBalance(account);
        } else {
            throw new IllegalArgumentException(
                    String.format("Не может быть отрицательной суммы! Указанная сумма: %.2f", sum));
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
                    throw new InsufficientFundsException(
                            String.format("На балансе счета %s недостаточно средств!", account.getAccountNumber()));
                account.getFromAccount(sum);
                System.out.printf("Со счета %s снята сумма: %.2f.\n", accountNumber, sum);
                Transaction.printBalance(account);
            } else {
                throw new IllegalArgumentException(
                        String.format("Не может быть отрицательной суммы! Указанная сумма: %.2f", sum));
            }
        } else {
            System.out.printf("Счет %s не найден.\n", accountNumber);
        }
    }

    private Account getAccountByAccountNumber(String accountNumber) {
        for (Account account: advancedAccountsList) {
            if (account.getAccountNumber() == accountNumber)
                return account;
        }
        return null;
    }
}
