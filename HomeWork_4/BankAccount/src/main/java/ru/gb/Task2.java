package ru.gb;

import ru.gb.exceptions.InsufficientFundsException;
import ru.gb.exceptions.WrongAccountNumberException;
import ru.gb.management.AdvancedAccountManagement;

/**
 * 2*.Создать несколько типов счетов, унаследованных от Account, например: CreditAcciunt, DebitAccount.
 * Создать класс (Transaction), позволяющий проводить транзакции между счетами (переводить сумму с одного
 * счета на другой)
 * Класс Transaction должен возбуждать исключение в случае неудачной попытки перевести деньги с одного счета на другой.
 * Продемонстрируйте работу вашего приложения:
 * Программа должна обрабатывать все исключения с помощью конструкции try-catch, выводя соответствующие сообщения
 * об ошибках.
 */
public class Task2 {
    public static void main(String[] args) {
        AdvancedAccountManagement advancedAccountManagement = new AdvancedAccountManagement();

        try {
            advancedAccountManagement.createCreditAccount("Ivan Petrov", "1234567890");
            advancedAccountManagement.createDebitAccount("Petr Ivanov", "1234567899", 2000);
            advancedAccountManagement.doTransaction("1234567890", "1234567899", 5000);
        } catch (WrongAccountNumberException | IllegalArgumentException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

    }
}
