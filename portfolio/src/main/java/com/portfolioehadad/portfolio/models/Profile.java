package com.portfolioehadad.portfolio.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profiles")
@Getter @Setter
public class Profile {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
     @OneToOne
     @JoinColumn(name = "user_id")
     private UserPorfolio user;
    private String name;
    private String image;
    private String profession;

}
