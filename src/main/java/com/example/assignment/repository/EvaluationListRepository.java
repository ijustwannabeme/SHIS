package com.example.assignment.repository;

import com.example.assignment.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationListRepository extends JpaRepository<EvaluationList, EvaluationListId> {
    List<EvaluationList> findByScoreDateStartingWith(String month);
}
