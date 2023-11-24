package ru.gb.registry;

import ru.gb.comporators.ComparatorBySalary;
import ru.gb.comporators.ComparatorBySecondName;
import ru.gb.workers.Employee;
import ru.gb.workers.Freelancer;
import ru.gb.workers.Worker;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class Registry implements Iterable {
    Collection<Employee> employees;

    public Registry() {
        employees = new TreeSet<>(new ComparatorBySecondName());
    }

    public void addFreelancer(String name, String secondName, String patronymic, String birthday, double salary) {
        employees.add(Freelancer.create(name, secondName, patronymic, birthday, salary));
    }

    public void addWorker(String name, String secondName, String patronymic, String birthday, double salary) {
        employees.add(Worker.create(name, secondName, patronymic, birthday, salary));
    }

    public void printEmployeesBySecondName() {
        System.out.println("Вывод сотрудников отсортированных по фамили:");
        System.out.println("-------------------------------------------------------------------------");
        for (Employee employee: employees) {
            System.out.println(employee);
            System.out.printf("Среднемесячная оплата труда: %.2f\n", employee.getAverageMonthlySalary());
            System.out.println("-------------------------------------------------------------------------");
        }
    }

    public void printEmployeesBySalary() {
        System.out.println("Вывод сотрудников отсортированных по среднемесячной зарплате:");
        Collection<Employee> employees2 = new TreeSet<>(new ComparatorBySalary());
        employees2.addAll(employees);
        System.out.println("-------------------------------------------------------------------------");
        for (Employee employee: employees2) {
            System.out.println(employee);
            System.out.printf("Среднемесячная оплата труда: %.2f\n", employee.getAverageMonthlySalary());
            System.out.println("-------------------------------------------------------------------------");
        }
    }

    @Override
    public Iterator<Employee> iterator() {
        return employees.iterator();
    }
}
