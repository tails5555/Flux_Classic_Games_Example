package net.kang.main.controller;

import net.kang.main.domain.Company;
import net.kang.main.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("company")
public class CompanyController {
    @Autowired CompanyService companyService;
    @GetMapping("all_company")
    public Flux<Company> allCompany(){
        return companyService.findAll();
    }
    @GetMapping("one_company/{id}")
    public Mono<Company> oneCompany(@PathVariable("id") String id){
        return companyService.findOne(id);
    }
}
