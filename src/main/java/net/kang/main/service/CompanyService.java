package net.kang.main.service;

import net.kang.main.domain.Company;
import net.kang.main.repository.CompanyRepository;
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
    public void update(Company company){
        companyRepository.findById(company.getId())
            .map(c -> {
                c.setName(company.getName());
                c.setFoundDate(company.getFoundDate());
                c.setAddress(company.getAddress());
                return c;
            })
            .flatMap(c -> companyRepository.save(c));
    }
}
