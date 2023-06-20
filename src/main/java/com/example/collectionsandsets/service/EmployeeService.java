package com.example.collectionsandsets.service;

import com.example.collectionsandsets.exception.EmployeeAlreadyAddedException;
import com.example.collectionsandsets.exception.EmployeeNotFoundException;
import com.example.collectionsandsets.exception.EmployeeStorageIsFullException;
import com.example.collectionsandsets.exception.InvalidInputException;
import com.example.collectionsandsets.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;


@Service
public class EmployeeService {
    private static final int MAX_SIZE = 5;
    private final Map<String, Employee> employees;

    public EmployeeService() {
        Employee employees1 = new Employee("Inna", "Ivaniva", 2222, 2);
        Employee employees2 = new Employee("Anna", "Olegovna", 3333, 2);
        Employee employees3 = new Employee("Janna", "Sergeevna", 4444, 1);
        this.employees = new HashMap<>();
    }

    private static boolean correctName(String firstName, String lastName) {
        return isAlpha(firstName) && isAlpha(lastName);
    }

    public Employee add(String firstName, String lastName, double salary, int department) {
        if (!correctName(firstName, lastName)) {
            throw new InvalidInputException();
        }

        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employeeToAdd = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employeeToAdd.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employeeToAdd.getFullName(), employeeToAdd);
        return employeeToAdd;
    }

    public Employee remove(String firstName, String lastName, double salary, int department) {
        if (!correctName(firstName, lastName)) {
            throw new InvalidInputException();
        }
        Employee employeeToRemove = new Employee(firstName, lastName, salary, department);
        if (!employees.containsKey(employeeToRemove.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employeeToRemove.getFullName(), employeeToRemove);
        return employeeToRemove;
    }

    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    public Employee find(String firstName, String lastName) {
        if (!correctName(firstName, lastName)) {
            throw new InvalidInputException();
        }
        for (Employee employee : employees.values()) {
            if (firstName.equalsIgnoreCase(employee.getFirstName())
                    && lastName.equalsIgnoreCase(employee.getLastName())) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }
}
