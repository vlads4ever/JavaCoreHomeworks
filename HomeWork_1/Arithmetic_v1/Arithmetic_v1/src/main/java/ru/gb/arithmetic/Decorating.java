package ru.gb.arithmetic;

/**
 * Класс красиво оформляет вывод результатов вычислений.
 */
public class Decorating {
    /**
     *
     * @param sum декорируемая сумма
     * @param diff декорируемая разность
     * @param prod декорируемое произведение
     * @param quot декорируемое частное
     * @return Возвращается декорируемая строка.
     */
    public static String decorate(int sum, int diff, int prod, int quot) {
        return String.format("Your sum is: %d \n" +
                            "Your difference is: %d \n" +
                            "Your product is: %d \n" +
                            "Your quotient is: %d", sum, diff, prod, quot);
    }
}
