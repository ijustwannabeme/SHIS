package com.example.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "change_history")
@IdClass(ChangeHistoryId.class)
public class ChangeHistory {
    @Id
    @Column(name = "score_date")
    private String scoreDate;

    @Id
    @Column(name = "agent_id")
    private Integer agentId;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

    @Column(name = "change_value")
    private String changeValue;

    @Column(name = "change_date")
    private String changeDate;

}

