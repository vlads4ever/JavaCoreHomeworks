package ru.gb.workers;

public class Freelancer extends Employee{

    protected Freelancer(String name, String secondName, String patronymic, String birthday) {
        super(name, secondName, patronymic, birthday);
    }

    public static Freelancer create(String name, String secondName, String patronymic, String birthday) {
        return new Freelancer(name, secondName, patronymic, birthday);
    }

    @Override
    public double getAverageMonthlySalary() {
        return 20.8 * 8 * super.getSalary();
    }
}
