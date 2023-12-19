package ru.skypro.CollectionEmployees.Service;

import org.springframework.stereotype.Service;
import ru.skypro.CollectionEmployees.Employee;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeAlreadeAddeException;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeNotFounException;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeStorageIsFullException;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int MAX_SIZE = 10;

    @PostConstruct
    public void initEmployees() {
        add("Ivan", "Ivanov", 150_000, 1);
        add("Andrey", "Petrov", 250_000, 1);
        add("Max", "Mikuloz", 100_000, 1);

        add("Sergey", "Sadov", 190_000, 2);
        add("Vlad", "Truxan", 180_000, 2);

        add("Vladimir", "Truxanov", 40_000, 3);
    }

    private final Map<String, Employee> employees = new HashMap<>();


    @Override
    public Employee add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Хранилище заполнено.");
        }
        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadeAddeException("Сотрудник уже имеется.");
        }
        Employee employee = new Employee(firstName, lastName, salary, department);
        employees.put(getKey(employee), employee);//добавить
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        if (!employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadeAddeException("Сотрудник не найден в хранилище.");
        }
        return employees.remove(getKey(firstName, lastName)); //удалить
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(getKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFounException("Сотрудник " + firstName + " " + lastName + " не найден.");
        }
        return employee;
    }

    @Override
    public Map<String, Employee> getAll() {
        return Collections.unmodifiableMap(employees);
    }

    private static String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    private static String getKey(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }
}

