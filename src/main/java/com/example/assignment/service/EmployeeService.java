package com.example.assignment.service;

import com.example.assignment.entity.Employee;
import com.example.assignment.entity.EmployeeId;
import com.example.assignment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByAgentId(Integer agentId) {
        return employeeRepository.findByAgentId(agentId)
                .orElseThrow(() -> new RuntimeException("Employee not found with agentId: " + agentId));
    }

    public Employee getEmployeeByAgentIdAndTeamCode(Integer agentId, String teamCode) {
        return employeeRepository.findByAgentIdAndTeamCode(agentId, teamCode)
                .orElseThrow(() -> new RuntimeException("Employee not found with agentId: " + agentId + " and teamCode: " + teamCode));
    }
}