package net.kang.main.mongodb.controller;

import net.kang.main.model.GameForm;
import net.kang.main.mongodb.service.GameService;
import net.kang.main.mongodb.vo.GameVO;
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
@RequestMapping("game")
public class GameController {
    @Autowired GameService gameService;

    @GetMapping("all_game")
    public Flux<GameVO> allGame(){
        return gameService.findAll();
    }

    @GetMapping("one_game/{id}")
    public Mono<ResponseEntity<GameVO>> oneGame(@PathVariable("id") String id){
        return gameService.findOne(id).map(gameVO -> {
            return ResponseEntity.ok(gameVO);
        }).defaultIfEmpty(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("create_game")
    public Mono<ResponseEntity<GameVO>> createGame(@Valid @RequestBody GameForm gameForm){
        return gameService.create(gameForm).map(g -> {
            return new ResponseEntity<GameVO>(g, HttpStatus.CREATED);
        }).defaultIfEmpty(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping("update_game/{id}")
    public Mono<ResponseEntity<GameVO>> updateGame(@PathVariable("id") String id, @Valid @RequestBody GameForm gameForm){
        return gameService.update(id, gameForm).map(g -> {
            return ResponseEntity.ok(g);
        }).defaultIfEmpty(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @DeleteMapping("delete_game/{id}")
    public Mono<ResponseEntity<Void>> deleteGame(@PathVariable("id") String id){
        return gameService.delete(id).then(
                Mono.just(new ResponseEntity<Void>(HttpStatus.OK))
        ).defaultIfEmpty(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }
}
