package net.kang.main.mongodb.service;

import net.kang.main.model.ConsoleForm;
import net.kang.main.mongodb.vo.ConsoleVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConsoleService {
    public Flux<ConsoleVO> findAll();
    public Mono<ConsoleVO> findOne(String id);
    public Mono<ConsoleVO> create(ConsoleForm consoleForm);
    public Mono<ConsoleVO> update(String id, ConsoleForm consoleForm);
    public Mono<Void> delete(String id);
}
