package net.kang.main.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;

@Data
@Document(collection="company")
public class Company {
    @Id
    String id;
    String name;
    String address;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    Date foundDate;
}
