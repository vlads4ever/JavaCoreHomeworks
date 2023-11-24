package ru.gb;

import ru.gb.registry.Registry;
import ru.gb.workers.Employee;

public class Main {
    public static void main(String[] args) {

        Registry registry = new Registry();

        registry.addFreelancer(
                "Василий",
                "Смирнов",
                "Алибабаевич",
                "1980-10-15",
                1500.00);
        registry.addWorker(
                "Петр",
                "Кондратов",
                "Анатольевич",
                "1975-06-11",
                150000.00);
        registry.addFreelancer(
                "Софья",
                "Шпак",
                "Васильевна",
                "1995-03-08",
                900.00);
        registry.addWorker(
                "Анна",
                "Пасечникова",
                "Дмитриевна",
                "1968-07-27",
                130000.00);


        registry.printEmployeesBySecondName();
        System.out.println();
        registry.printEmployeesBySalary();

        System.out.println("Демонстрация итеративности регистра сотрудников");
        for (Employee employee : (Iterable<Employee>) registry) {
            System.out.println(employee);
        }


    }
}