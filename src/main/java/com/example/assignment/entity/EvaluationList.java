package com.example.assignment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "evaluation_list")
@IdClass(EvaluationListId.class)
public class EvaluationList {
    @Id
    @Column(name = "score_date")
    private String scoreDate;

    @Id
    @Column(name = "agent_id")
    private Integer agentId;

    @Id
    @Column(name = "team_code")
    private String teamCode;

    @Column(name = "total_score")
    private Integer totalScore;

    @Column(name = "ranking")
    private Integer ranking;

    // Getters and setters
}

