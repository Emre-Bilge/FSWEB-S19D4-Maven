package com.workintech.s19d1.repository;

import com.workintech.s19d1.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

// @Query("SELECT m FROM Movie m WHERE m.name=:name")
// Optional<Movie> findByName(String name);
}
