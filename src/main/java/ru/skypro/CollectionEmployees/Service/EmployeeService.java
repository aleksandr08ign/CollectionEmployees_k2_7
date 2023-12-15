package ru.skypro.CollectionEmployees.Service;

import ru.skypro.CollectionEmployees.Employee;

import java.util.Collection;
import java.util.Map;

public interface EmployeeService {
    Employee add(String firstName, String lastName);
    Employee remove(String firstName, String lastName);
    Employee find(String firstName, String lastName);

    Map<String, Employee> getAll();


}
