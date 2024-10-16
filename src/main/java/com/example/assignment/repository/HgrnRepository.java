package com.example.assignment.repository;

import com.example.assignment.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HgrnRepository extends JpaRepository<Hgrn, HgrnId> {
    Optional<Hgrn> findByTeamCode(String teamCode);
}
