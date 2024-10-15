package com.example.assignment.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "employee")
@IdClass(EmployeeId.class)
public class Employee {
    @Id
    @Column(name = "agent_id")
    private Integer agentId;

    @Id
    @Column(name = "team_code")
    private String teamCode;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name = "status")
    private String status;

    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "team_code", insertable = false, updatable = false)
    private Team team;

    // Getters and setters
}

