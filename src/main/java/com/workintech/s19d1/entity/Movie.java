package com.workintech.s19d1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="movie",schema="fsweb")
public class Movie {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id")
    private Long id;

    @NotNull(message = "name null olamaz")
    @NotBlank
    @Size(min=2,max=45)
    @Column(name="name")
    private String name;

    @NotNull(message = "directorname null olamaz")
    @NotBlank
    @Size(min=3,max=45)
    @Column(name="director_name")
    private String directorName;

    @Column(name="rating")
    private Integer rating;

    @Column(name="release_date")
    private LocalDate releaseDate;

@JsonBackReference
@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
        fetch = FetchType.EAGER)
    @JoinTable(name="movie_actor",schema="fsweb",
    joinColumns=@JoinColumn(name="movie_id"),
    inverseJoinColumns = @JoinColumn(name="actor_id"))
    private List<Actor> actors ;


public void addActor(Actor actor){
    if(actors == null){
        actors = new ArrayList<>();
    }
    actors.add(actor);
}
}
