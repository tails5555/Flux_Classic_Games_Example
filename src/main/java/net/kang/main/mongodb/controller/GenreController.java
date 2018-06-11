package net.kang.main.mongodb.controller;

import net.kang.main.mongodb.service.GenreService;
import net.kang.main.mongodb.vo.GenreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("genre")
public class GenreController {
    @Autowired GenreService genreService;

    @GetMapping("all_genre")
    public Flux<GenreVO> allGenre(){
        return genreService.findAll();
    }

    @GetMapping("one_genre/{id}")
    public Mono<ResponseEntity<GenreVO>> oneGenre(@PathVariable("id") String id){
        return genreService.findOne(id).map(genre -> {
            return ResponseEntity.ok(genre);
        }).defaultIfEmpty(
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("create_genre")
    public Mono<ResponseEntity<GenreVO>> createGenre(@Valid @RequestBody GenreVO genreVO){
        return genreService.create(genreVO).map(g -> {
            return new ResponseEntity<GenreVO>(g, HttpStatus.CREATED);
        }).defaultIfEmpty(
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping("update_genre/{id}")
    public Mono<ResponseEntity<GenreVO>> updateGenre(@PathVariable("id") String id, @Valid @RequestBody GenreVO genreVO){
        return genreService.update(id, genreVO).map(g -> {
            return ResponseEntity.ok(g);
        }).defaultIfEmpty(
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @DeleteMapping("delete_genre/{id}")
    public Mono<ResponseEntity<Void>> deleteGenre(@PathVariable("id") String id){
        return genreService.delete(id).then(
            Mono.just(new ResponseEntity<Void>(HttpStatus.OK))
        ).defaultIfEmpty(
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }
}
