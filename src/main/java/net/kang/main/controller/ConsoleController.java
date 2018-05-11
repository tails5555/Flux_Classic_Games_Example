package net.kang.main.controller;

import net.kang.main.domain.Console;
import net.kang.main.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("console")
public class ConsoleController {
    @Autowired ConsoleService consoleService;

    @GetMapping("all_console")
    public Flux<Console> allConsole(){
        return consoleService.findAll();
    }

    @GetMapping("one_console/{id}")
    public Mono<ResponseEntity<Console>> oneConsole(@PathVariable("id") String id){
        return consoleService.findOne(id).map(console -> {
            return ResponseEntity.ok(console);
        }).defaultIfEmpty(
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }
}
