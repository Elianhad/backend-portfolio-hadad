package com.portfolioehadad.portfolio.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.print.PrinterJob;
import java.util.List;


@Entity
@Table(name = "users")
@Getter @Setter
public class UserPorfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Profile profile;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Education> educations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private  List<Project> projects;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private  List<Skill> skills;
}
