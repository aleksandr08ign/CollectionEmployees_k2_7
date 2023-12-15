package ru.skypro.CollectionEmployees.Service;

import org.springframework.stereotype.Service;
import ru.skypro.CollectionEmployees.Employee;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeAlreadeAddeException;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeNotFounException;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int MAX_SIZE = 3;

    private final List<Employee> employeeList;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {

        if (employeeList.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Хранилище заполнено.");
        }
            Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadeAddeException("Сотрудник уже имеется.");
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employeeList.contains(employee)) {
            throw new EmployeeAlreadeAddeException("Сотрудник не найден в хранилище.");
        }
        employeeList.remove(employee);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFounException("Сотрудник " + firstName +" "+ lastName + " не найден.");
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employeeList);
    }
}
