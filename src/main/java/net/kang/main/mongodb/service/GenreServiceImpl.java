package net.kang.main.mongodb.service;

import net.kang.main.mongodb.repository.GenreRepository;
import net.kang.main.mongodb.vo.GenreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GenreServiceImpl implements GenreService{
    @Autowired GenreRepository genreRepository;
    @Override
    public Flux<GenreVO> findAll(){
        return genreRepository.findAll().map(genre -> GenreVO.builtToGenreVO(genre));
    }

    @Override
    public Mono<GenreVO> findOne(String id){
        return genreRepository.findById(id).map(genre -> GenreVO.builtToGenreVO(genre));
    }

    @Override
    public Mono<GenreVO> create(GenreVO genreVO){
        return genreRepository.insert(GenreVO.builtToGenre(genreVO)).map(genre -> GenreVO.builtToGenreVO(genre));
    }

    @Override
    public Mono<GenreVO> update(String id, GenreVO genreVO){
        return genreRepository.findById(id)
                .flatMap(g -> {
                    g.setName(genreVO.getName());
                    return genreRepository.save(g).map(genre -> GenreVO.builtToGenreVO(genre));
                });
    }

    @Override
    public Mono<Void> delete(String id){
        return genreRepository.findById(id)
                .flatMap(g -> {
                    return genreRepository.delete(g);
                });
    }
}
