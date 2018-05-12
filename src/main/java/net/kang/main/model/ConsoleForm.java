package net.kang.main.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ConsoleForm {
    String name;
    double price;
    int bitCount;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date releaseDate;
    String companyId;
}
