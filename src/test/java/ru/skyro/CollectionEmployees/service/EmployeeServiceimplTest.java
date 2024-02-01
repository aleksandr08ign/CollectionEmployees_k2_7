package ru.skyro.CollectionEmployees.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skypro.CollectionEmployees.Employee;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeAlreadeAddeException;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeStorageIsFullException;
import ru.skypro.CollectionEmployees.Service.EmployeeServiceImpl;

import java.util.Optional;

public class EmployeeServiceimplTest {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Test
    public void exceptionEmloyeeAllReadeAddTest() {
        Employee employee = new Employee("Vasiliy", "Dastov", 12000, 2);
        employeeService.add(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        Assertions.assertThrows(EmployeeAlreadeAddeException.class, () -> {
            employeeService.add(
                    employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment()
            );
        });
    }

    @Test
    public void correctFindEmployee() {
        Employee employee = new Employee("Vasiliy", "Dastov", 12000, 2);
        employeeService.add(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        employeeService.find(employee.getFirstName(), employee.getLastName());

        Employee actualEmployee = employeeService.find(employee.getFirstName(), employee.getLastName());

        Assertions.assertEquals(employee, actualEmployee);
    }

    @Test
    public void correctAddEmployee() {
        Employee employee = new Employee("Vasiliy", "Dastov", 12000, 2);

        Employee actualEmployee = employeeService.add(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        Assertions.assertEquals(employee, actualEmployee);
    }

    @Test
    public void correctMAX_SIZE() {
        int MAX_SIZE = 3;

        Employee employee = new Employee("Vasiliy", "Dastov", 12000, 1);

        employeeService.add(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());


        if (MAX_SIZE <= employeeService.getAll().size()) {
            throw new EmployeeStorageIsFullException("max");
        }
    }

}



