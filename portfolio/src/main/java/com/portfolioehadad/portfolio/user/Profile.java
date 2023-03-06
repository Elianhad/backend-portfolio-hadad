package com.portfolioehadad.portfolio.user;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Profile extends User {
    String name;
    String profession;
    String image;
    @OneToOne(mappedBy = "profile")
    private Long userId;


}
