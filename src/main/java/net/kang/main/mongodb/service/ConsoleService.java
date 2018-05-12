package net.kang.main.mongodb.service;

import net.kang.main.model.ConsoleForm;
import net.kang.main.mongodb.domain.Company;
import net.kang.main.mongodb.domain.Console;
import net.kang.main.mongodb.repository.CompanyRepository;
import net.kang.main.mongodb.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConsoleService {
    @Autowired ConsoleRepository consoleRepository;
    @Autowired CompanyRepository companyRepository;

    // Reactive MongoDB에서 DBRef를 제공하지 않아서 데이터의 무결성을 확립하기 위해 이를 따로 작성하였다.
    public Console integrityConfirm(Console console){
        if (console.getCompany() != null) {
            Company curCompany = console.getCompany();
            Company newCompany = companyRepository.findById(console.getCompany().getId())
                    .defaultIfEmpty( new Company() )
                    .block();

            if(!curCompany.equals(newCompany)) {
                console.setCompany(newCompany);
                return consoleRepository.save(console).block();
            }
        }
        return console;
    }

    public Flux<Console> findAll(){
        return consoleRepository.findAll().map(console -> {
            return integrityConfirm(console);
        });
    }

    public Mono<Console> findOne(String id){
        return consoleRepository.findById(id).map(console -> {
            return integrityConfirm(console);
        });
    }

    // Reactive MongoDB에서는 DBRef에 대해서 작동할 수 있는 방안에 대해 제공을 하지 않고 있다.
    // 이 함수에 대한 문제 중에서 ConsoleForm에서 Mono로 주고 받을 때에 대해 반영이 제대로 안 되어 있기 때문에 이에 대해서는 공부를 더 해보고 수정을 할 예정이다.
    public Mono<Console> create(ConsoleForm consoleForm){
        // 새로 저장할 콘솔(게임 기기)를 생성한다.
        Console console = new Console();
        // 게임 기기 제조 회사는 Mono를 이용해서 탐색을 하되 회사 정보가 없는 경우에는 빈 회사 껍질을 반환하고, 회사가 존재하면 그냥 반환을 해 준다.
        Company company = companyRepository.findById(consoleForm.getCompanyId())
                .defaultIfEmpty( new Company() )
                .block();

        // 게임 기기에 대해서 각 Field에 대해 설정을 한다.
        console.setName(consoleForm.getName());
        console.setBitCount(consoleForm.getBitCount());
        console.setPrice(consoleForm.getPrice());
        console.setReleaseDate(consoleForm.getReleaseDate());

        // 회사에 대한 정보가 있는 경우에만 따로 설정을 한다.
        if(!company.equals(new Company()))
            console.setCompany(company);

        return consoleRepository.insert(console);
    }

    public Mono<Console> update(String id, ConsoleForm consoleForm){
        // 게임 기기 제조 회사는 Mono를 이용해서 탐색을 하되 회사 정보가 없는 경우에는 빈 회사 껍질을 반환하고, 회사가 존재하면 그냥 반환을 해 준다.
        Company company = companyRepository.findById(consoleForm.getCompanyId())
                .defaultIfEmpty( new Company() )
                .block();

        return consoleRepository.findById(id)
            .flatMap(console -> {
                console.setName(consoleForm.getName());
                console.setBitCount(consoleForm.getBitCount());
                console.setPrice(consoleForm.getPrice());
                console.setReleaseDate(consoleForm.getReleaseDate());
                // 회사에 대한 정보가 있는 경우에만 따로 설정을 한다.
                if(!company.equals(new Company()))
                    console.setCompany(company);
                return consoleRepository.save(console);
            });
    }

    public Mono<Void> delete(String id){
        return consoleRepository.findById(id)
            .flatMap(c -> {
                return consoleRepository.delete(c);
            });
    }
}

