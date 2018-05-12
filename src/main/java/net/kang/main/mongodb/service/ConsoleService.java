package net.kang.main.mongodb.service;

import net.kang.main.mongodb.domain.Console;
import net.kang.main.mongodb.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConsoleService {
    @Autowired ConsoleRepository consoleRepository;
    public Flux<Console> findAll(){
        return consoleRepository.findAll();
    }
    public Mono<Console> findOne(String id){
        return consoleRepository.findById(id);
    }

}