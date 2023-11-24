package ru.gb.workers;

public class Worker extends Employee{

    protected Worker(String name, String secondName, String patronymic, String birthday, double salary) {
        super(name, secondName, patronymic, birthday, salary);
    }

    public static Worker create(String name, String secondName, String patronymic, String birthday, double salary) {
        return new Worker(name, secondName, patronymic, birthday, salary);
    }

    @Override
    public double getAverageMonthlySalary() {
        return super.getSalary();
    }
}
