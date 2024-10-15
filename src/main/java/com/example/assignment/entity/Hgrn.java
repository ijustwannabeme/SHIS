package com.example.assignment.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "hgrn")
@IdClass(Hgrn.class)
public class Hgrn {
    @Id
    @Column(name = "hgrn_team_code")
    private String hgrnTeamCode;

    @Id
    @Column(name = "team_code")
    private String teamCode;

    @Column(name = "hgrn_team_name")
    private String hgrnTeamName;

}
