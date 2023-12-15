package ru.skypro.CollectionEmployees.Service;

import org.springframework.stereotype.Service;
import ru.skypro.CollectionEmployees.Employee;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeAlreadeAddeException;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeNotFounException;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int MAX_SIZE = 3;

    private final Map<String, Employee> employees = new HashMap<>();


    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Хранилище заполнено.");
        }
        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadeAddeException("Сотрудник уже имеется.");
        }
        Employee employee = new Employee(firstName, lastName);
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

