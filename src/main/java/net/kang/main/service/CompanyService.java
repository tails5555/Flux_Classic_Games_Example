package net.kang.main.service;

import net.kang.main.domain.Company;
import net.kang.main.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CompanyService {
    @Autowired CompanyRepository companyRepository;
    public Flux<Company> findAll(){
        return companyRepository.findAll();
    }
}
