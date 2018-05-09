package net.kang.main.repository;

import net.kang.main.domain.Console;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsoleRepository extends MongoRepository<Console, String> {

}
