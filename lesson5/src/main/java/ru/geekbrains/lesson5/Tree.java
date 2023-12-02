package ru.geekbrains.lesson5;

import java.io.File;

/**
 * TODO: Доработать метод print, необходимо распечатывать директории и файлы
 */
public class Tree {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        print(new File("."), "", true, ANSI_RESET);
    }

    static void print(File file, String indent, boolean isLast, String color){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
        }

        System.out.println(color + file.getName() + ANSI_RESET);


        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory()){
                print(files[i], indent, files.length == ++subDirCounter, ANSI_YELLOW);
            } else {
                print(files[i], indent, files.length == ++subDirCounter, ANSI_GREEN);
            }
        }
    }

}
