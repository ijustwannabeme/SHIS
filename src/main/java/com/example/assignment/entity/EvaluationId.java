package com.example.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class EvaluationId implements Serializable {
    private String scoreDate;
    private Integer agentId;
    private String teamCode;

    // Constructors, equals, and hashCode methods
}
