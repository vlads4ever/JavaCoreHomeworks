package ru.gb;

import ru.gb.arithmetic.Arithmetic;
import ru.gb.arithmetic.Decorating;

/**
 * Основной класс приложения с точкой входа и запуском основных вычислительных действий.
 */
public class Main {
    /**
     * Точка входа в программу.
     *
     * @param args передаваемые программе аргументы
     */
    public static void main(String[] args) {
        System.out.println("Simple calculator for two numbers: 2 and 2");
        int sum = Arithmetic.sum(2, 2);
        int diff = Arithmetic.difference(2,2);
        int prod = Arithmetic.product(2,2);
        int quot = Arithmetic.quotient(2,2);
        System.out.println(Decorating.decorate(sum, diff, prod, quot));
    }
}