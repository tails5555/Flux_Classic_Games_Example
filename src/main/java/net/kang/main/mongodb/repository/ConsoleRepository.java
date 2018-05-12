package net.kang.main.mongodb.repository;

import net.kang.main.mongodb.domain.Console;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsoleRepository extends ReactiveMongoRepository<Console, String> {

}
