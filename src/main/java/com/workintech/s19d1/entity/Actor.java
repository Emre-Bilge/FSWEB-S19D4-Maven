package com.workintech.s19d1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name="actor",schema="fsweb")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id ;

    @NotNull
    @NotBlank
    @Size(min=3,max=45)
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min=3,max=45)
    @Column(name="last_name")
    private String lastName;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="birth_date")
    private LocalDate birthDate;

    @JsonManagedReference
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name="movie_actor",schema="fsweb",
            joinColumns=@JoinColumn(name="actor_id"),
            inverseJoinColumns = @JoinColumn(name="movie_id"))
    private List<Movie> movies ;


    public void addMovie(Movie movie){
        if(movies == null){
            movies = new ArrayList<>();
        }
        movies.add(movie);
    }

}
