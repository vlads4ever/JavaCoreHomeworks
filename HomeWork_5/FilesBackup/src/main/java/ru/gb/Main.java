package ru.gb;


import java.io.*;

/**
 * 1. Написать функцию, создающую резервную копию всех файлов в директории во вновь созданную папку ./backup
 */
public class Main {
    public static void main(String[] args) {

//        if (!new File("./backup").exists())
//            if (new File("./backup").mkdir())
//                System.out.println("Директория для резервного копирования создана по пути: ./backup");

        File targetDirectory = new File("backup");

        try {
            backupFolder("SourceDirectory", "backup");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void backupFolder(String sourceDirectory, String targetDirectory ) throws IOException {
        File currentDir = new File(sourceDirectory);
        File[] files = currentDir.listFiles();
        if (files == null) {
            System.out.println("В директории нет файлов.");
        } else {
            for (int i = 0; i < files.length; i++){
                if (files[i].isFile()){
                    System.out.println("Файл: " + files[i].getName() + " Находится в папке: " + files[i].getParent());
                    copyFile(files[i], targetDirectory);
                    System.out.println("Файл: " + files[i].getName() + " скопирован в папку: " + targetDirectory);
                } else {
                    System.out.println("Папка: " + files[i].getName() + " Находится в папке: " + files[i].getParent());
                }
            }
        }
    }

    static void copyFile(File sourceFile, String targetDirectory) throws IOException{
        File targetFile = new File(targetDirectory + "/" + sourceFile.getName());
        // На запись
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetFile)){
            int c;
            // На чтение
            try (FileInputStream fileInputStream = new FileInputStream(sourceFile)){
                while ((c = fileInputStream.read()) != -1){
                    fileOutputStream.write(c);
                }
            }
        }
    }
}