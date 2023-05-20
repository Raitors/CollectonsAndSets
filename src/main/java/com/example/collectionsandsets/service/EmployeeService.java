package com.example.collectionsandsets.service;

import com.example.collectionsandsets.exception.EmployeeAlreadyAddedException;
import com.example.collectionsandsets.exception.EmployeeNotFoundException;
import com.example.collectionsandsets.exception.EmployeeStorageIsFullException;
import com.example.collectionsandsets.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private static final int MAX_SIZE = 5;
    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employeeToAdd = new Employee(firstName, lastName);
        if (employees.contains(employeeToAdd)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employeeToAdd);
        return employeeToAdd;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employees.contains(employeeToRemove)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employeeToRemove);
        return employeeToRemove;
    }

    public Employee find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (firstName.equalsIgnoreCase(employee.getFirstName())
                    && lastName.equalsIgnoreCase(employee.getLastName())) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> getAll() {
        return Collections.unmodifiableList(employees);
    }
}
