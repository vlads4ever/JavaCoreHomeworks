package ru.geekbrains.core.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Program {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';

    private static final int WIN_COUNT = 4;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static  char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;


    public static void main(String[] args) {
        while (true){
            initialize();
            printField();
            while (true){
                humanTurn();
                printField();
                if (checkGameState(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (checkGameState(DOT_AI, "Победил компьютер!"))
                    break;
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }
    }

    /**
     * Инициализация игрового поля
     */
    static void initialize(){
        fieldSizeY = 5;
        fieldSizeX = 5;

        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++){
            for (int x = 0; x < fieldSizeX; x++){
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать текущего состояния игрового поля
     */
    private static void printField(){
        System.out.print("+");
        for (int i = 0; i < fieldSizeX; i++){
            System.out.print("-" + (i + 1));
        }
        System.out.println("-");

        for (int y = 0; y < fieldSizeY; y++){
            System.out.print(y + 1 + "|");
            for (int x = 0; x < fieldSizeX; x++){
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Ход игрока (человека)
     */
    static void humanTurn(){
        int x;
        int y;

        do {
            System.out.printf("Введите координаты хода X (от 1 до %d) и Y (от 1 до %d)" +
                    "\nчерез пробел: ", fieldSizeX, fieldSizeY);
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));

        field[y][x] = DOT_HUMAN;
    }

    /**
     * Случайный ход игрока (компьютера)
     */
    static void randomAiTurn(){
        int x;
        int y;

        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));

        field[y][x] = DOT_AI;
    }

    /**
     * Ход игрока (компьютера)
     */
    static void aiTurn(){
        // проверка на опережение на 2 хода
        int checkFieldsCount = WIN_COUNT - 2;

        for (int y = 0; y < fieldSizeY; y++){
            for (int x = 0; x < fieldSizeX; x++){
                if (checkHorizontalWin(x, y, DOT_HUMAN, checkFieldsCount)) {
                    if (isCellValid(x - 1, y) && isCellEmpty(x - 1, y)) {
                        field[y][x - 1] = DOT_AI;
                        break;
                    } else if (isCellValid(x + checkFieldsCount, y) && isCellEmpty(x + checkFieldsCount, y)) {
                        field[y][x + checkFieldsCount] = DOT_AI;
                        break;
                    }
                } else if (checkVerticalWin(x, y, DOT_HUMAN, checkFieldsCount)) {
                    if (isCellValid(x, y - 1) && isCellEmpty(x, y - 1)) {
                        field[y - 1][x] = DOT_AI;
                        break;
                    } else if (isCellValid(x, y + checkFieldsCount) && isCellEmpty(x, y + checkFieldsCount)) {
                        field[y + checkFieldsCount][x] = DOT_AI;
                        break;
                    }
                } else if (checkUpDiagonalWin(x, y, DOT_HUMAN, checkFieldsCount)) {
                    if (isCellValid(x - 1, y + 1) && isCellEmpty(x - 1, y + 1)) {
                        field[y + 1][x - 1] = DOT_AI;
                        break;
                    } else if (isCellValid(x + checkFieldsCount, y - checkFieldsCount) &&
                            isCellEmpty(x + checkFieldsCount, y - checkFieldsCount)) {
                        field[y - checkFieldsCount][x + checkFieldsCount] = DOT_AI;
                        break;
                    }
                } else if (checkDownDiagonalWin(x, y, DOT_HUMAN, checkFieldsCount)) {
                    if (isCellValid(x - 1, y - 1) && isCellEmpty(x - 1, y - 1)) {
                        field[y - 1][x - 1] = DOT_AI;
                        break;
                    } else if (isCellValid(x + checkFieldsCount, y + checkFieldsCount) &&
                            isCellEmpty(x + checkFieldsCount, y + checkFieldsCount)) {
                        field[y + checkFieldsCount][x + checkFieldsCount] = DOT_AI;
                        break;
                    }
                }
            }
        }

        // Если играть на опережение не получается, то компьютер ходит случайно
        randomAiTurn();
    }

    /**
     * Проверка, является ли ячейка игрового поля пустой
     * @param x
     * @param y
     * @return
     */
    static boolean isCellEmpty(int x, int y){
        return field[y][x] == DOT_EMPTY;
    }

    /**
     * Проверка доступности ячейки игрового поля
     * @param x
     * @param y
     * @return
     */
    static boolean isCellValid(int x, int y){
        return x >= 0 && x< fieldSizeX && y >= 0 && y < fieldSizeY;
    }


    /**
     * Метод проверки состояния игры
     * @param dot фишка игрока
     * @param s победный слоган
     * @return результат проверки состояния игры
     */
    static boolean checkGameState(char dot, String s){
        if (checkWin(dot)){
            System.out.println(s);
            return true;
        }
        if (checkDraw()){
            System.out.println("Ничья!");
            return true;
        }
        return false; // Игра продолжается
    }

    /**
     * Проверка на ничью
     * @return
     */
    static boolean checkDraw(){
        for (int y = 0; y < fieldSizeY; y++){
            for (int x = 0; x < fieldSizeX; x++){
               if (isCellEmpty(x, y))
                   return false;
            }
        }
        return true;
    }

    /**
     * Проверка победы игрока
     * @param dot фишка игрока
     * @return признак победы
     */
    static boolean checkWin(char dot){
        for (int y = 0; y < fieldSizeY; y++){
            for (int x = 0; x < fieldSizeX; x++){
                if (checkHorizontalWin(x, y, dot, WIN_COUNT) ||
                    checkVerticalWin(x, y, dot, WIN_COUNT) ||
                    checkUpDiagonalWin(x, y, dot, WIN_COUNT) ||
                    checkDownDiagonalWin(x, y, dot, WIN_COUNT)) return true;
            }
        }
        return false;
    }

    /**
     * Проверка победы по горизонтали
     * @param x
     * @param y
     * @param dot фишка игрока
     * @param winCount победное количество проверяемых фишек
     * @return
     */
    static boolean checkHorizontalWin(int x, int y, char dot, int winCount){
        if (!isCellValid(x + winCount - 1, y)) return false;
        for (int i = 0; i < winCount; i++) {
            if (field[y][x + i] != dot) return false;
        }
        return true;
    }

    /**
     * Проверка победы по вертикали
     * @param x
     * @param y
     * @param dot фишка игрока
     * @param winCount победное количество проверяемых фишек
     * @return
     */
    static boolean checkVerticalWin(int x, int y, char dot, int winCount){
        if (!isCellValid(x, y + winCount - 1)) return false;
        for (int i = 0; i < winCount; i++) {
            if (field[y + i][x] != dot) return false;
        }
        return true;
    }

    /**
     * Проверка победы по диагонали вверх
     * @param x
     * @param y
     * @param dot фишка игрока
     * @param winCount победное количество проверяемых фишек
     * @return
     */
    static boolean checkUpDiagonalWin(int x, int y, char dot, int winCount){
        if (!isCellValid(x + winCount - 1, y - winCount + 1)) return false;
        for (int i = 0; i < winCount; i++) {
            if (field[y - i][x + i] != dot) return false;
        }
        return true;
    }

    /**
     * Проверка победы по диагонали вниз
     * @param x
     * @param y
     * @param dot фишка игрока
     * @param winCount победное количество проверяемых фишек
     * @return
     */
    static boolean checkDownDiagonalWin(int x, int y, char dot, int winCount){
        if (!isCellValid(x + winCount - 1, y + winCount - 1)) return false;
        for (int i = 0; i < winCount; i++) {
            if (field[y + i][x + i] != dot) return false;
        }
        return true;
    }



}
