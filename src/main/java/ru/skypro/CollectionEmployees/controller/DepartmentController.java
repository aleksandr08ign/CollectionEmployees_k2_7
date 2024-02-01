package ru.skypro.CollectionEmployees.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.CollectionEmployees.Employee;
import ru.skypro.CollectionEmployees.Service.DepartmentService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{id}/salary/sum")
    public Integer getEmployeeSumSalary(@PathVariable("id") Integer id) {
        return departmentService.getEmployeeSumSalary(id);
    }

    @GetMapping("{id}/salary/min")
    public Integer getEmployeeWithMinSalary(@PathVariable("id") Integer id) {
        return departmentService.getEmployeeWithMinSalary(id).getSalary();
    }

    @GetMapping("{id}/salary/max")
    public Integer getEmployeeWithMaxSalary(@PathVariable("id") Integer id) {
        return departmentService.getEmployeeWithMaxSalary(id).getSalary();
    }

    @GetMapping("{id}/employees")
    public List<Employee> getAllEmployeeByDepartmets(@PathVariable("id") Integer id) {
        return departmentService.getAllEmployeeByDepartment(id);
    }

    @GetMapping("employees")
    public Map<Integer, List<Employee>> getAllEmployee() {
        return departmentService.getAllEmployee();
    }
}
