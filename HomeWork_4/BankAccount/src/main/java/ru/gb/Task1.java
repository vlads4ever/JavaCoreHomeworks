package ru.gb;

import ru.gb.exceptions.InsufficientFundsException;
import ru.gb.exceptions.WrongAccountNumberException;
import ru.gb.management.AccountManagement;

/**
 * 1. Создать программу управления банковским счетом (Account).
 * Программа должна позволять пользователю вводить начальный баланс счета, сумму депозита и сумму снятия средств.
 * При этом она должна обрабатывать следующие исключительные ситуации:
 * Попытка создать счет с отрицательным начальным балансом должна вызывать исключение IllegalArgumentException
 * с соответствующим сообщением.
 * Попытка внести депозит с отрицательной суммой должна вызывать исключение IllegalArgumentException с
 * соответствующим сообщением.
 * Попытка снять средства, сумма которых превышает текущий баланс, должна вызывать исключение
 * InsufficientFundsException с сообщением о недостаточных средствах и текущим балансом.
 * Продемонстрируйте работу вашего приложения:
 * Программа должна обрабатывать все исключения с помощью конструкции try-catch, выводя соответствующие сообщения об ошибках.
 */
public class Task1 {
    public static void main(String[] args) {
        AccountManagement accountManagement = new AccountManagement();

        System.out.println("Пример валидной операции со счетами:");
        try {
            accountManagement.createCommonAccount("Ivan Petrov", "1234567890", 10000);
            accountManagement.putToAccount("1234567890",350);
            accountManagement.getFromAccount("1234567890",5000);
        } catch (WrongAccountNumberException | IllegalArgumentException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-------------------------------------------------------------------\n");

        System.out.println("Исключение: Неправильный формат счета:");
        try {
            accountManagement.createCommonAccount("Petr Ivanov", "123456qqqq", 2000);
        } catch (WrongAccountNumberException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-------------------------------------------------------------------\n");

        System.out.println("Исключение: Неправильный начальный баланс счета:");
        try {
            accountManagement.createCommonAccount("Petr Ivanov", "1234567899", -2000);
        } catch (WrongAccountNumberException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-------------------------------------------------------------------\n");

        System.out.println("Исключение: Отрицательная сумма операции:");
        try {
            accountManagement.createCommonAccount("Petr Ivanov", "1234567899", 1000);
            accountManagement.putToAccount("1234567890",-350);
        } catch (WrongAccountNumberException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-------------------------------------------------------------------\n");

        System.out.println("Исключение: Недостаточно средств на счете:");
        try {
            accountManagement.createCommonAccount("Vasiliy Samoylov", "3334567899", 2000);
            accountManagement.getFromAccount("3334567899",5000);
        } catch (WrongAccountNumberException | IllegalArgumentException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-------------------------------------------------------------------\n");

    }
}
