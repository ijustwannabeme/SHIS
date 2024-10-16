package com.example.assignment.repository;

import com.example.assignment.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, EvaluationId> {
    List<Evaluation> findByScoreDateStartingWith(String month);

    List<Evaluation> findByAgentId(Integer agentId);

    List<Evaluation> findByScoreDateStartingWithAndIsTargetFixedTrue(String month);

    List<Evaluation> findByScoreDateStartingWithAndTeamCodeAndIsTargetFixedTrue(String month, String teamCode);

    List<Evaluation> findByScoreDateStartingWithAndIsTargetFixedTrueOrderByRankingAsc(String month);

    List<Evaluation> findByScoreDateStartingWithAndTeamCodeAndIsTargetFixedTrueOrderByRankingAsc(String month, String teamCode);


}