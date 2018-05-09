package net.kang.main.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Document(collection="console")
public class Console {
    @Id
    String id;
    String name;
    double price;
    int bitCount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date releaseDate;

    @DBRef(db="flux_classic_games", lazy=false)
    Company company;

}
