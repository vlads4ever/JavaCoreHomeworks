package ru.gb.management;

import ru.gb.account.Account;
import ru.gb.exceptions.InsufficientFundsException;
import ru.gb.validations.Validation;

public class Transaction {
    public static void moneyTransfer(Account fromAccount, Account toAccount, double sum)
            throws IllegalArgumentException, InsufficientFundsException {
        if (Validation.checkSum(sum)) {
            if (!Validation.checkBalance(fromAccount, sum))
                throw new InsufficientFundsException("На балансе счета недостаточно средств!");
            fromAccount.getFromAccount(sum);
            toAccount.putToAccount(sum);
            System.out.printf("Перевод со счета %s на счет %s осуществлен на сумму: %.2f.\nБаланс счета %s: %.2f.\nБаланс счета %s: %.2f.\n",
                    fromAccount.getAccountNumber(), toAccount.getAccountNumber(), sum,
                    fromAccount.getAccountNumber(), fromAccount.getBalance(),
                    toAccount.getAccountNumber(), toAccount.getBalance());
        } else {
            throw new IllegalArgumentException("Не может быть отрицательной суммы!");
        }
    }

}
