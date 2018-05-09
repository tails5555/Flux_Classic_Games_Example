package net.kang.main.repository;

import net.kang.main.domain.Console;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsoleRepository extends MongoRepository<Console, String> {
}
