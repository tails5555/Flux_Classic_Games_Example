package net.kang.main.mongodb.service;

import net.kang.main.mongodb.vo.GenreVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenreService {
    public Flux<GenreVO> findAll();
    public Mono<GenreVO> findOne(String id);
    public Mono<GenreVO> create(GenreVO genreVO);
    public Mono<GenreVO> update(String id, GenreVO genreVO);
    public Mono<Void> delete(String id);
}
