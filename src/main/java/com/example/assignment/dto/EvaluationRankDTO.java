package com.example.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EvaluationRankDTO {
    private String agentName;
    private Integer agentId;
    private String hgrnTeamName;
    private String teamName;
    private Integer totalScore;
    private Integer ranking;
}