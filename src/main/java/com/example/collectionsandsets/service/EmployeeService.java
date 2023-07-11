package com.example.collectionsandsets.service;

import com.example.collectionsandsets.exception.EmployeeAlreadyAddedException;
import com.example.collectionsandsets.exception.EmployeeNotFoundException;
import com.example.collectionsandsets.exception.EmployeeStorageIsFullException;
import com.example.collectionsandsets.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmployeeService {
    private static final int MAX_LIMIT = 5;
    private final Map<String, Employee> employees = new HashMap<>(MAX_LIMIT);

    public EmployeeService() {
        Employee employee1 = new Employee("iv", "iva", 1, 1111);
        Employee employee2 = new Employee("ol", "ola", 1, 2222);
        Employee employee3 = new Employee("az", "aza", 2, 333);
        Employee employee4 = new Employee("es", "esa", 2, 444);
      /*  employees.put(createKey(employee1), employee1);
        employees.put(createKey(employee2), employee2);
        employees.put(createKey(employee3), employee3);
        employees.put(createKey(employee4), employee4);*/

    }

    private static String createKey(Employee employee) {
        return createKey(employee.getFirstName(), employee.getLastName());
    }

    private static String createKey(String firstName, String lastName) {
        return (firstName + lastName).toLowerCase();
    }

    public List<Employee> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(employees.values()));
    }

    public Employee add(Employee employee) {
        if (employees.size() >= MAX_LIMIT) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(createKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(employee), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        return employees.remove(createKey(firstName, lastName));

    }

    public Employee find(String firstName, String lastName) {
        if (!employees.containsKey((createKey(firstName, lastName)))) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(createKey(firstName, lastName));
    }
}
