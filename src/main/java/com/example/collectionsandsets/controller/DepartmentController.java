package com.example.collectionsandsets.controller;

import com.example.collectionsandsets.model.Employee;
import com.example.collectionsandsets.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam("department") int department) {
        return departmentService.getEmploteeWithMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam("department") int department) {
        return departmentService.getEmploteeWithMinSalary(department);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> getAll(@RequestParam("departmentId") int department) {
        return departmentService.getAll(department);
    }

    @GetMapping("/all")
    private Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }
}
