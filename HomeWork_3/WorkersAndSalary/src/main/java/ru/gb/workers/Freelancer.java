package ru.gb.workers;

public class Freelancer extends Employee{

    protected Freelancer(String name, String secondName, String patronymic, String birthday, double salary) {
        super(name, secondName, patronymic, birthday, salary);
    }

    public static Freelancer create(String name, String secondName, String patronymic, String birthday, double salary) {
        return new Freelancer(name, secondName, patronymic, birthday, salary);
    }

    @Override
    public double getAverageMonthlySalary() {
        return 20.8 * 8 * super.getSalary();
    }
}
