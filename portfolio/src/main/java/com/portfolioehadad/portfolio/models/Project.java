package com.portfolioehadad.portfolio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameProject;
    private String imageProject;
    @ManyToMany
    private List<Skill> skillsProject;
    @ManyToOne
    @JsonBackReference
    private  UserPorfolio user;
}
