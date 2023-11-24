package ru.gb.workers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Employee {
    private String name;
    private String secondName;
    private String patronymic;
    private LocalDate birthday;
    private DateTimeFormatter datePattern;
    private double salary;

    protected Employee(String name, String secondName, String patronymic, String birthday, double salary) {
        this.name = name;
        this.secondName = secondName;
        this.patronymic = patronymic;
        datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.birthday = LocalDate.parse(birthday, datePattern);
        this.salary = salary;
    }

    public abstract double getAverageMonthlySalary();

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public DateTimeFormatter getDatePattern() {
        return datePattern;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Имя: '" + name + '\'' +
                ", Фамилия: '" + secondName + '\'' +
                ", Отчество: '" + patronymic + '\'' +
                ", Дата рождения: " + birthday +
                ", Зарплата: " + salary;
    }
}
