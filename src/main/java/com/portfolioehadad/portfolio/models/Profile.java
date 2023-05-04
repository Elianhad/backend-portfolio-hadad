package com.portfolioehadad.portfolio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profile")
@Getter @Setter
public class Profile {
    @Id
    private Long id;
    private String name;
    private String image;
    private String profession;
    private String about;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private UserPorfolio user;
}
