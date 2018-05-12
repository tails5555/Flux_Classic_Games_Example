package net.kang.main.mongodb.repository;

import net.kang.main.mongodb.domain.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

}
