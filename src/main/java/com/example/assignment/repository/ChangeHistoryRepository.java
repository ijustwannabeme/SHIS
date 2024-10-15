package com.example.assignment.repository;

import com.example.assignment.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChangeHistoryRepository extends JpaRepository<ChangeHistory, ChangeHistoryId> {
    List<ChangeHistory> findByScoreDateStartingWith(String month);
}