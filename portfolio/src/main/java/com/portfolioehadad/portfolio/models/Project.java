package com.portfolioehadad.portfolio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;
/**
 *
 * proyecto deberá
 * contener el nombre,
 * fecha de realización,
 * descripción del
 * proyecto y el link a su
 * evidencia, en este caso
 * podrías adjuntar
 * imágenes de ellos
 * */
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
