package com.example.assignment.repository;

import com.example.assignment.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, EmployeeId> {
    Optional<Employee> findByAgentIdAndTeamCode(Integer agentId, String teamCode);
    Optional<Employee> findByAgentId(Integer agentId);
}