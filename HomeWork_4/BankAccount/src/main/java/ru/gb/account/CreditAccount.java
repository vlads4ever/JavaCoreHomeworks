package ru.gb.account;

import ru.gb.validations.Validation;

/**
 * Класс кредитного счета с устанавливаемым лимитом и признаком начисления процента на кредит
 */
public class CreditAccount extends Account {
    private double limit;
    private boolean isCredit;

    public CreditAccount(String name, String accountNumber, double limit) {
        super(name, accountNumber, limit);
        isCredit = false;
    }

    @Override
    public void getFromAccount(double sum) {
        super.getFromAccount(sum);
        if (super.getBalance() < limit)
            isCredit = true;
    }

    @Override
    public void putToAccount(double sum) {
        super.putToAccount(sum);
        if (super.getBalance() >= limit)
            isCredit = false;
    }

    public double getLimit() {
        return limit;
    }

    public boolean isCredit() {
        return isCredit;
    }
}
