package com.example.assignment.repository;

import com.example.assignment.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, EvaluationId> {
    List<Evaluation> findByScoreDateStartingWith(String month);
    List<Evaluation> findByAgentId(Integer agentId);
}

