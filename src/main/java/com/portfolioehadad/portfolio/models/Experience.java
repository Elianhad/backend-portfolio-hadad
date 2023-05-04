package com.portfolioehadad.portfolio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.h2.engine.User;

/**
 *  Titulo del puesto,
 * periodo, logo de la empresa
 * si lo encuentras, y
 * descripción de las
 * actividades realizadas en ese
 * puest
 * */
@Entity
@Table(name = "experience")
@Getter @Setter
public class Experience {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String title;
   private String description;
   private String date;

   @ManyToOne
   @JsonBackReference
   private UserPorfolio user;
}
