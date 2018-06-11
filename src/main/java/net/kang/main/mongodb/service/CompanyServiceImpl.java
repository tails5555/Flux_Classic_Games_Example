package net.kang.main.mongodb.service;

import net.kang.main.mongodb.repository.CompanyRepository;
import net.kang.main.mongodb.vo.CompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired CompanyRepository companyRepository;

    @Override
    public Flux<CompanyVO> findAll(){
        return companyRepository.findAll().map(company -> CompanyVO.builtToCompanyVO(company));
    }

    @Override
    public Mono<CompanyVO> findOne(String id){
        return companyRepository.findById(id).map(company -> CompanyVO.builtToCompanyVO(company));
    }

    @Override
    public Mono<CompanyVO> create(CompanyVO companyVO){
        return companyRepository.insert(CompanyVO.builtToCompany(companyVO)).map(company -> CompanyVO.builtToCompanyVO(company));
    }

    @Override
    public Mono<CompanyVO> update(String id, CompanyVO companyVO){
        return companyRepository.findById(id)
                .flatMap(c -> {
                    c.setName(companyVO.getName());
                    c.setFoundDate(companyVO.getFoundDate());
                    c.setAddress(companyVO.getAddress());
                    return companyRepository.save(c);
                }).map(company -> CompanyVO.builtToCompanyVO(company));
    }

    @Override
    public Mono<Void> delete(String id){
        return companyRepository.findById(id)
                .flatMap(c -> {
                    return companyRepository.delete(c);
                });
    }
}
