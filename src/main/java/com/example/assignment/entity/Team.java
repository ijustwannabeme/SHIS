package com.example.assignment.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "team")
public class Team {
    @Id
    @Column(name = "team_code")
    private String teamCode;

    @Column(name = "team_name")
    private String teamName;

    // Getters and setters
}