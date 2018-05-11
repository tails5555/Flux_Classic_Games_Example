package net.kang.main.repository;

import net.kang.main.domain.Console;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsoleRepository extends ReactiveMongoRepository<Console, String> {

}
