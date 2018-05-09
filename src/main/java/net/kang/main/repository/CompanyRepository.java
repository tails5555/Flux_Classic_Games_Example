package net.kang.main.repository;

import net.kang.main.domain.Company;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CompanyRepository extends ReactiveMongoRepository<Company, String> {
    Flux<Company> findAll();
}
