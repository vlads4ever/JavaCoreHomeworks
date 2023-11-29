package ru.gb.management;

import ru.gb.account.Account;
import ru.gb.account.CreditAccount;
import ru.gb.account.DebitAccount;
import ru.gb.exceptions.InsufficientFundsException;
import ru.gb.validations.Validation;

public class Transaction {
    public static void moneyTransfer(Account fromAccount, Account toAccount, double sum)
            throws IllegalArgumentException, InsufficientFundsException {
        if (Validation.checkSum(sum)) {
            if (!Validation.checkBalance(fromAccount, sum))
                throw new InsufficientFundsException(
                        String.format("На балансе счета %s недостаточно средств!", fromAccount.getAccountNumber()));

            if (fromAccount instanceof CreditAccount) {
                ((CreditAccount) fromAccount).getFromAccount(sum);
            } else {
                ((DebitAccount) fromAccount).getFromAccount(sum);
            }

            if (toAccount instanceof CreditAccount) {
                ((CreditAccount) toAccount).putToAccount(sum);
            } else {
                ((DebitAccount) toAccount).putToAccount(sum);
            }

            System.out.printf("Перевод со счета %s на счет %s осуществлен на сумму: %.2f.\n",
                    fromAccount.getAccountNumber(), toAccount.getAccountNumber(), sum);
        } else {
            throw new IllegalArgumentException(
                    String.format("Не может быть отрицательной суммы! Указанная сумма: %.2f", sum));
        }
    }

    public static void printBalance(Account account) {
        if (account instanceof CreditAccount) {
            System.out.printf("Баланс кредитного счета %s: %.2f.\n", account.getAccountNumber(), account.getBalance());
        } else {
            System.out.printf("Баланс дебитового счета %s: %.2f.\n", account.getAccountNumber(), account.getBalance());
        }
    }

}
