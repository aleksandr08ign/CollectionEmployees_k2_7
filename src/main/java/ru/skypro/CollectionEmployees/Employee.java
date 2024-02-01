package ru.skypro.CollectionEmployees;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private int size;
    private final int salary;
    private final int department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return size == employee.size && salary == employee.salary && department == employee.department && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, size, salary, department);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartment() {
        return department;
    }

    public Employee(String firstName, String lastName, int salary, int department) {
        this.firstName = StringUtils.capitalize(firstName);
        this.lastName = StringUtils.capitalize(lastName);
        this.salary = salary;
        this.department = department;
    }
    public String getFullName() {
        return firstName + lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", size=" + size +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}
