package ru.gb.validations;

import ru.gb.account.Account;
import ru.gb.account.CreditAccount;
import ru.gb.account.DebitAccount;

public class Validation {

    /**
     * Проверка на наличие 10 цифр в номере счета
     * @param accountNumber номер счета
     * @return
     */
    public static boolean checkAccountNumber(String accountNumber) {
        return accountNumber.length() == 10 && accountNumber.matches("[0-9]{10}");
    }

    /**
     * Проверка на неотрицательность передаваемой суммы
     * @param sum проверяемая сумма
     * @return
     */
    public static boolean checkSum(double sum) {
        return sum >= 0;
    }

    /**
     * Проверка на наличие средств на обычном счете
     * @param account передаваемый на проверку счет
     * @param sum сумма
     * @return
     */
    public static boolean checkBalance(Account account, double sum) {
        return account.getBalance() >= sum;
    }

}
