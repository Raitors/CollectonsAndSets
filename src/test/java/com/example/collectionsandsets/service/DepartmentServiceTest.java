package com.example.collectionsandsets.service;

import com.example.collectionsandsets.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    private static final Collection<Employee> EMPLOYEES = Arrays.asList(
            new Employee("ivan", "ivanov", 1, 0.1),
            new Employee("oleg", "sergeevech", 1, 0.2),
            new Employee("masha", "ivanova", 2, 1500),
            new Employee("slava", "ivanov", 2, 2500),
            new Employee("andrey", "andreev", 3, 2500)
    );
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void init() {
        when(employeeService.getAll()).thenReturn((List<Employee>) EMPLOYEES);
    }

    @Test
    void sum() {
        double actual = departmentService.getEmployeesSalarySum(1);
        assertEquals(0.3, actual, 0.00001);
    }

    @Test
    void max() {
        double actual = departmentService.getEmployeeMaxSalary(2);
        assertEquals(2500, actual);
    }

    @Test
    void min() {
        double actual = departmentService.getEmployeeMinSalary(2);
        assertEquals(1500, actual);
    }

    @Test
    void getAllByDepartment() {
        List<Employee> actual = departmentService.getAll(3);
        Collection<Employee> excpected = Collections.singletonList(new Employee("andrey", "andreev", 3, 2500));
        assertIterableEquals(excpected, actual);
    }

    @Test
    void getAllByWrongDepartment() {
        List<Employee> all = departmentService.getAll(4);
        assertTrue(all.isEmpty());
    }

    @Test
    void getAll() {
        Map<Integer, List<Employee>> actual = departmentService.getAll();
        Employee andrey = new Employee("andrey", "andreev", 3, 2500);
        assertTrue(actual.get(3).contains(andrey));
        assertFalse(actual.get(2).contains(andrey));

        assertEquals(3, actual.keySet().size());
    }

}



