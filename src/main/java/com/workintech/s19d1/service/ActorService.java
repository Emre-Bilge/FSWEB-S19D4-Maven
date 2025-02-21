package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    Actor save(Actor actor);

    List<Actor> findAll();

    Actor findById(long id);


    void delete(Actor actor);


}
