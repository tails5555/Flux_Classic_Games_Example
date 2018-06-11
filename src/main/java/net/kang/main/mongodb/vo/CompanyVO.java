package net.kang.main.mongodb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kang.main.mongodb.domain.Company;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyVO {
    String id;
    String name;
    String address;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date foundDate;

    public static CompanyVO builtToCompanyVO(Company company){
        return new CompanyVO(company.getId(), company.getName(), company.getAddress(), company.getFoundDate());
    }

    public static Company builtToCompany(CompanyVO companyVO){
        return new Company(companyVO.getId(), companyVO.getName(), companyVO.getAddress(), companyVO.getFoundDate());
    }
}
