package com.example.assignment.repository;

import com.example.assignment.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {}

