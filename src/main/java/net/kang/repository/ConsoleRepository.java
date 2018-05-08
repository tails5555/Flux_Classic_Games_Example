package net.kang.repository;

import net.kang.domain.Console;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsoleRepository extends MongoRepository<Console, String> {
}
