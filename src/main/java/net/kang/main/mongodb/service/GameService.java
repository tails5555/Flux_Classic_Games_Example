package net.kang.main.mongodb.service;

import net.kang.main.model.GameForm;
import net.kang.main.mongodb.vo.GameVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameService {
    public Flux<GameVO> findAll();
    public Mono<GameVO> findOne(String id);
    public Mono<GameVO> create(GameForm gameForm);
    public Mono<GameVO> update(String id, GameForm gameForm);
    public Mono<Void> delete(String id);
}
