package ru.gb.arithmetic;

/**
 * В классе производятся арифметические действия.
 */
public class Arithmetic {
    /**
     *
     * @param a первое слагаемое
     * @param b второе слагаемое
     * @return сумма
     */
    public static int sum(int a, int b) {
        return a + b;
    }

    /**
     *
     * @param a уменьшаемое
     * @param b вычитаемое
     * @return разность
     */
    public static int difference(int a, int b) {
        return a - b;
    }

    /**
     *
     * @param a первый множитель
     * @param b второй множитель
     * @return произведение
     */
    public static int product(int a, int b) {
        return a * b;
    }

    /**
     *
     * @param a делимое
     * @param b делитель
     * @return частное
     */
    public static int quotient(int a, int b) {
        return a / b;
    }
}
