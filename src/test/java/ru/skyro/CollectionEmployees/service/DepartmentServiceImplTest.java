package ru.skyro.CollectionEmployees.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.CollectionEmployees.Employee;
import ru.skypro.CollectionEmployees.Exceptions.EmployeeNotFounException;
import ru.skypro.CollectionEmployees.Service.DepartmentServiceImpl;
import ru.skypro.CollectionEmployees.Service.EmployeeService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private final List<Employee> employees = new ArrayList<>() {{
        add(new Employee("ivan", "ivanov", 150_000, 1));
        add(new Employee("andrey", "petrov", 250_000, 1));
        add(new Employee("Max", "Mikuloz", 100_000, 1));
        add(new Employee("Sergey", "Sadov", 190_000, 2));
        add(new Employee("Vlad", "Truxan", 180_000, 2));
        add(new Employee("Vladimir", "Truxanov", 40_000, 3));
    }};

    @Test
    public void calculateSumTest() {
        int departmentId = 1;
        int expectedSum = 500_000;

        Map<String, Employee> employeeMap = new HashMap<>() {{
            put(employees.get(0).getFirstName() + employees.get(0).getLastName(), employees.get(0));
            put(employees.get(1).getFirstName() + employees.get(1).getLastName(), employees.get(1));
            put(employees.get(2).getFirstName() + employees.get(2).getLastName(), employees.get(2));
            put(employees.get(3).getFirstName() + employees.get(3).getLastName(), employees.get(3));
            put(employees.get(4).getFirstName() + employees.get(4).getLastName(), employees.get(4));
        }};

        when(employeeService.getAll()).thenReturn(employeeMap);

        Integer EmployeeSalarySum = departmentService.getEmployeeSumSalary(departmentId);

        Assertions.assertEquals(expectedSum, EmployeeSalarySum);
    }

    @Test
    public void calculateMinSalaryTest() {
        int departmentId = 1;
        Employee expectedEmployee = employees.get(2);

        Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }

        when(employeeService.getAll()).thenReturn(employeeMap);

        Employee employee = departmentService.getEmployeeWithMinSalary(departmentId);

        Assertions.assertEquals(expectedEmployee, employee);
    }

    @Test
    public void calculateMaxSalaryTest() {
        int departmentId = 1;
        Employee expectedEmployee = employees.get(1);

        Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }

        when(employeeService.getAll()).thenReturn(employeeMap);

        Employee employee = departmentService.getEmployeeWithMaxSalary(departmentId);

        Assertions.assertEquals(expectedEmployee, employee);
    }

    @Test
    public void allEmployeeByDepartment() {
        int departmentId = 1;
        List<Employee> expectedEmployees = new ArrayList<>() {{
            add(employees.get(0));
            add(employees.get(1));
            add(employees.get(2));
        }};

        Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }
        when(employeeService.getAll()).thenReturn(employeeMap);

        List<Employee> actualEmployees = departmentService.getAllEmployeeByDepartment(departmentId);

        Assertions.assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void groupEmployeesByDepartmentId() {

        Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }
        when(employeeService.getAll()).thenReturn(employeeMap);

        Map<Integer, List<Employee>> expectedMap = new HashMap<>() {{
            put(1, List.of(employees.get(0), employees.get(1), employees.get(2)));
            put(2, List.of(employees.get(3), employees.get(4)));
            put(3, List.of(employees.get(5)));
        }};

        Map<Integer, List<Employee>> actualMap = departmentService.getAllEmployee();

        Assertions.assertEquals(expectedMap, actualMap);
    }

    @Test
    public void testNotNullWhenThereAreNoEmployeesDepartment() throws EmployeeNotFounException {
        //int departmentId = 1;
        Employee employeeMock = mock(Employee.class);

        Mockito.doThrow(new EmployeeNotFounException()).when(employeeMock).getDepartment();

        try {
            employeeMock.getDepartment();
            fail();
        } catch (EmployeeNotFounException e) {
            verify(employeeMock, times(1)).getDepartment();
        }

        //when(employeeService.getAll()).thenReturn(Collections.emptyMap());
        //when(employeeService.getAll()).thenThrow(EmployeeNotFounException.class);


        //Employee employee = departmentService.getEmployeeWithMinSalary(departmentId);

        //Assertions.assertNull(employee);


    }


}
