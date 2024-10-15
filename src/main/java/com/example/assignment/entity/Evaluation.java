package com.example.assignment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "evaluation")
@IdClass(EvaluationId.class)
public class Evaluation {
    @Id
    @Column(name = "score_date")
    private String scoreDate;

    @Id
    @Column(name = "agent_id")
    private Integer agentId;

    @Id
    @Column(name = "team_code")
    private String teamCode;

    @Getter
    @Column(name = "score1")
    private Integer score1;

    @Getter
    @Column(name = "score2")
    private Integer score2;

    @Column(name = "is_score_fixed")
    private Boolean isScoreFixed;

    @Column(name = "is_target_fixed")
    private Boolean isTargetFixed;

}

