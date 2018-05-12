package net.kang.main.mongodb.service;

import net.kang.main.mongodb.domain.Company;
import net.kang.main.mongodb.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompanyService {
    @Autowired CompanyRepository companyRepository;

    public Flux<Company> findAll(){
        return companyRepository.findAll();
    }

    public Mono<Company> findOne(String id){
        return companyRepository.findById(id);
    }

    public Mono<Company> create(Company company){
        return companyRepository.insert(company);
    }

    public Mono<Company> update(String id, Company company){
        return companyRepository.findById(id)
            .flatMap(c -> {
                c.setName(company.getName());
                c.setFoundDate(company.getFoundDate());
                c.setAddress(company.getAddress());
                return companyRepository.save(c);
            });
    }

    public Mono<Void> delete(String id){
        return companyRepository.findById(id)
            .flatMap(c -> {
                return companyRepository.delete(c);
            });
    }
}
