package com.portfolioehadad.portfolio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "skills")
@Getter
@Setter
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Strin nameSkill;
    private String imageSkill;
    private String percentageSkill;
    @ManyToOne
    @JsonBackReference
    private UserPorfolio user;
}
