package net.kang.main.repository;

import net.kang.main.domain.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

}
