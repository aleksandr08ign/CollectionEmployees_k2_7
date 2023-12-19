package ru.skypro.CollectionEmployees.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.CollectionEmployees.Employee;
import ru.skypro.CollectionEmployees.Service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam Integer departmentid) {
        return departmentService.getEmployeeWithMaxSalary(departmentid);
    }

    @GetMapping("min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam Integer departmentid) {
        return departmentService.getEmployeeWithMinSalary(departmentid);
    }

    @GetMapping(value = "all", params = "departmentid")
    public List<Employee> getAllEmployeeByDepartmets(@RequestParam Integer departmentid) {
        return departmentService.getAllEmployeeByDepartment(departmentid);
    }

    @GetMapping("all")
    public Map<Integer, List<Employee>> getAllEmployee() {
        return departmentService.getAllEmployee();
    }
}
