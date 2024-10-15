package com.example.assignment.controller;

import com.example.assignment.entity.Employee;
import com.example.assignment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/employees/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // 기타 엔드포인트들
}