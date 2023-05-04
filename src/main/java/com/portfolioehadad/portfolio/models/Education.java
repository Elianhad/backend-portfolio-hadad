package com.portfolioehadad.portfolio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "educations")
@Getter @Setter
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
    private String date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private UserPorfolio user;

}
