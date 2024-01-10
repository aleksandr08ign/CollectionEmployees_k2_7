package ru.skypro.CollectionEmployees.Service;

import ru.skypro.CollectionEmployees.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalary (Integer departmentid);
    Employee getEmployeeWithMinSalary (Integer departmentid);
    List<Employee> getAllEmployeeByDepartment (Integer departmentid);
    Map<Integer, List<Employee>> getAllEmployee ();
}
