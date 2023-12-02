package ru.gb;


import java.io.*;

/**
 * 1. Написать функцию, создающую резервную копию всех файлов в директории во вновь созданную папку ./backup
 */
public class Main {
    public static final String SOURCE_DIRECTORY = "./SourceDirectory";
    public static final String BACKUP_DIRECTORY = "./backup";

    public static void main(String[] args) {

        if (new File(BACKUP_DIRECTORY).mkdir())
            System.out.println("Директория для резервного копирования создана по пути: ./backup");

        try {
            doBackup(SOURCE_DIRECTORY, BACKUP_DIRECTORY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void doBackup(String sourceDirectory, String targetDirectory ) throws IOException {
        File currentDir = new File(sourceDirectory);
        File[] files = currentDir.listFiles();
        if (files == null) {
            System.out.println("В директории " + sourceDirectory + " нет файлов.");
        } else {
            for (int i = 0; i < files.length; i++){
                if (files[i].isFile()){
                    copyFile(files[i], targetDirectory);
                    System.out.println("Файл: " + files[i].getName() + " в папке: " + files[i].getParent() +
                            " скопирован в папку: " + targetDirectory);
                } else {
                    if (new File(targetDirectory + "/" + files[i].getName()).mkdir())
                        System.out.println("Поддиректория создана по пути: " + files[i].getPath());
                    String newTargetDirectory = targetDirectory + "/" + files[i].getName();
                    doBackup(files[i].getPath(), newTargetDirectory);
                }
            }
        }
    }

    static void copyFile(File sourceFile, String targetDirectory) throws IOException{
        File targetFile = new File(targetDirectory + "/" + sourceFile.getName());
        //Открываем поток на запись
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetFile)){
            int c;
            // Открываем поток на чтение
            try (FileInputStream fileInputStream = new FileInputStream(sourceFile)){
                while ((c = fileInputStream.read()) != -1){
                    fileOutputStream.write(c);
                }
            }
        }
    }
}