package ru.skypro.CollectionEmployees.Service;

import org.springframework.stereotype.Service;
import ru.skypro.CollectionEmployees.Employee;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeNotFounException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;


    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Integer getEmployeeSumSalary(Integer departmentid) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentid.equals(employee.getDepartment()))
                .map(Employee::getSalary)
                .mapToInt(Integer :: intValue)
                .sum();
    }

    @Override
    public Employee getEmployeeWithMaxSalary(Integer departmentid) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentid.equals(employee.getDepartment()))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFounException("нет сотрудников в указанном департаменте."));
        //.orElse(null);
    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer departmentid) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentid.equals(employee.getDepartment()))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFounException("нет сотрудников в указанном департаменте."));
    }

    @Override
    public List<Employee> getAllEmployeeByDepartment(Integer departmentid) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentid.equals(employee.getDepartment()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployee() {
        return employeeService.getAll().values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
