package net.kang.main.controller;

import net.kang.main.domain.Company;
import net.kang.main.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin
@RequestMapping("company")
public class CompanyController {
    @Autowired CompanyService companyService;
    @GetMapping("all_company")
    public Flux<Company> allCompany(){
        System.out.println(companyService.findAll());
        return companyService.findAll();
    }
    @GetMapping("index")
    public String hello(){
        return "hello";
    }
}
