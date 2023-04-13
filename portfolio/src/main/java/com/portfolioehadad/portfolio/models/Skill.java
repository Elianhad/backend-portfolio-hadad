package com.portfolioehadad.portfolio.models;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imageSkill;
    private String percentageSkill;
    @ManyToOne
    private UserPorfolio user;
    @ManyToMany
    private List<Project> projectsList;
}
