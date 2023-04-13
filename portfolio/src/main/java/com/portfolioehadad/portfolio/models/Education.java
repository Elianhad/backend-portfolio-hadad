package com.portfolioehadad.portfolio.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "educations")
public class Education {
    /*
    name:string,
    campus:string,
    date:Date,
    id:number
    */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String campus;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserPorfolio user;

}
