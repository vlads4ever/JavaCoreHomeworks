package ru.gb.comporators;

import ru.gb.workers.Employee;

import java.util.Comparator;

public class ComparatorBySecondName implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        int firstStage = o1.getSecondName().compareTo(o2.getSecondName());
        if (firstStage == 0) {
            int secondStage = o1.getName().compareTo(o2.getName());
            if (secondStage == 0) {
                return o1.getBirthday().compareTo(o2.getBirthday());
            }
            return secondStage;
        }
        return firstStage;
    }
}
