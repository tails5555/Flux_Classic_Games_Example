package net.kang.main.mongodb.service;

import net.kang.main.mongodb.vo.CompanyVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyService {
    public Flux<CompanyVO> findAll();
    public Mono<CompanyVO> findOne(String id);
    public Mono<CompanyVO> create(CompanyVO companyVO);
    public Mono<CompanyVO> update(String id, CompanyVO companyVO);
    public Mono<Void> delete(String id);
}
