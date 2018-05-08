package net.kang.controller;

import net.kang.domain.Company;
import net.kang.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("company")
public class CompanyController {
    @Autowired CompanyRepository companyRepository;

    @RequestMapping("findAll")
    public List<Company> findAll(){
        return companyRepository.findAll();
    }
}
