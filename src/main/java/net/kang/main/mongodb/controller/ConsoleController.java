package net.kang.main.mongodb.controller;

import net.kang.main.model.ConsoleForm;
import net.kang.main.mongodb.service.ConsoleService;
import net.kang.main.mongodb.vo.ConsoleVO;
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
@RequestMapping("console")
public class ConsoleController {
    @Autowired ConsoleService consoleService;

    @GetMapping("all_console")
    public Flux<ConsoleVO> allConsole(){
        return consoleService.findAll();
    }

    @GetMapping("one_console/{id}")
    public Mono<ResponseEntity<ConsoleVO>> oneConsole(@PathVariable("id") String id){
        return consoleService.findOne(id).map(console -> {
            return ResponseEntity.ok(console);
        }).defaultIfEmpty(
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("create_console")
    public Mono<ResponseEntity<ConsoleVO>> createConsole(@Valid @RequestBody ConsoleForm consoleForm){
        return consoleService.create(consoleForm).map(consoleVO -> {
            return new ResponseEntity<ConsoleVO>(consoleVO, HttpStatus.CREATED);
        }).defaultIfEmpty(
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping("update_console/{id}")
    public Mono<ResponseEntity<ConsoleVO>> updateConsole(@PathVariable("id") String id, @Valid @RequestBody ConsoleForm consoleForm){
        return consoleService.update(id, consoleForm)
            .map(consoleVO -> {
                return ResponseEntity.ok(consoleVO);
            }).defaultIfEmpty(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
            );
    }

    @DeleteMapping("delete_console/{id}")
    public Mono<ResponseEntity<Void>> deleteConsole(@PathVariable("id") String id){
        return consoleService.delete(id).then(
            Mono.just(new ResponseEntity<Void>(HttpStatus.OK))
        ).defaultIfEmpty(
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }
}
