package com.portfolioehadad.portfolio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "projects")
@Getter @Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameProject;
    private String imageProject;
    private String dateOfDevelop;
    private String description;
    private String linkTo;
    private List<String> skillsProject;
    @ManyToOne
    @JsonBackReference
    private  UserPorfolio user;
}
