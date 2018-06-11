package net.kang.main.mongodb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kang.main.mongodb.domain.Company;
import net.kang.main.mongodb.domain.Console;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsoleVO {
    String id;
    String name;
    double price;
    int bitCount;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date releaseDate;

    String companyName;
    String companyAddress;

    public static ConsoleVO builtToConsoleVO(Console console, Company company){
        return new ConsoleVO(console.getId(), console.getName(), console.getPrice(), console.getBitCount(), console.getReleaseDate(), company.getName(), company.getAddress());
    }
}
