package com.example.collectionsandsets.service;


import com.example.collectionsandsets.exception.EmployeeAlreadyAddedException;
import com.example.collectionsandsets.exception.EmployeeNotFoundException;
import com.example.collectionsandsets.exception.EmployeeStorageIsFullException;
import com.example.collectionsandsets.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();


    @Test
    void whenFullThenThrowException() {

        for (int i = 0; i < 5; i++) {
            char additionalChar = (char) ('a' + i);
            Employee employee = new Employee("name" + additionalChar, "second_name", 1, 1);
            employeeService.add(employee);
        }
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.add(new Employee("name", "last_name", 1, 1)));

    }

    @Test
    void whenNotUniqThenThrowException() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add(employee));
    }

    @Test
    void addPositive() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);
        assertTrue(employeeService.getAll().contains(employee));
    }

    @Test
    void findPositive() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);
        Employee actual = employeeService.find("name", "last_name");
        assertNotNull(actual);
        assertEquals(employee, actual);

    }

    @Test
    void findNegative() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("not_name", "not_last_name"));

    }

    @Test
    void removePositive() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);

        employeeService.remove("name", "last_name");

        assertFalse(employeeService.getAll().contains(employee));

    }

    @Test
    void removeNegative() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);

        Employee actual = employeeService.remove("not_name", "not_last_name");
        assertNull(actual);

    }

}

