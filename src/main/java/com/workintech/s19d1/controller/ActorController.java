package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;

import com.workintech.s19d1.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/actor")
public class ActorController {

    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<ActorResponse> findAll(){
        List<Actor> actors = actorService.findAll();
        return Converter.actorResponseConvert(actors);
    }

    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable Long id){
        Actor actor = actorService.findById(id);
     return Converter.actorResponseConvert(actor);
    }

    @PostMapping
    public ActorResponse save(@Validated @RequestBody ActorRequest actorRequest){
      Actor actor = actorRequest.getActor();
        List<Movie> movies = actorRequest.getMovies();
        for (Movie movie : movies){
            actor.addMovie(movie);
        }
        Actor savedActor = actorService.save(actor);
        return Converter.actorCreateResponseConvert(savedActor);
    }



    @PutMapping("/{id}")
    public ActorResponse update(@RequestBody Actor actor, @PathVariable Long id){
        Actor foundedActor = actorService.findById(id);
        actor.setMovies(foundedActor.getMovies());
        actor.setId(id);
        //Jpa daki save metodu hem save işlemini hem update işlemini yapıyor.
        actorService.save(actor);
        return Converter.actorResponseConvert(actor);
    }
    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable long id){
        Actor removedActor = actorService.findById(id);
        actorService.delete(removedActor);
        return Converter.actorResponseConvert(removedActor);
    }
}
