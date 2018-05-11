package net.kang.main.service;

import net.kang.main.domain.Genre;
import net.kang.main.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GenreService {
    @Autowired GenreRepository genreRepository;
    public Flux<Genre> findAll(){
        return genreRepository.findAll();
    }
    public Mono<Genre> findOne(String id){
        return genreRepository.findById(id);
    }
    public Mono<Genre> create(Genre genre){
        return genreRepository.insert(genre);
    }
    public Mono<Genre> update(String id, Genre genre){
        return genreRepository.findById(id)
            .flatMap(g -> {
                g.setName(genre.getName());
                return genreRepository.save(genre);
            });
    }
    public Mono<Void> delete(String id){
        return genreRepository.findById(id)
            .flatMap(g -> {
                return genreRepository.delete(g);
            });
    }
}
