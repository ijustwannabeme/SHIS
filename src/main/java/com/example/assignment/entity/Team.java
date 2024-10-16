package com.example.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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