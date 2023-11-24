package ru.gb.workers;

public class Worker extends Employee{

    protected Worker(String name, String secondName, String patronymic, String birthday) {
        super(name, secondName, patronymic, birthday);
    }

    public static Freelancer create(String name, String secondName, String patronymic, String birthday) {
        return new Freelancer(name, secondName, patronymic, birthday);
    }

    @Override
    public double getAverageMonthlySalary() {
        return super.getSalary();
    }
}
