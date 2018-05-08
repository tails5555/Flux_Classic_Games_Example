package net.kang.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Document(collection="game")
public class Game {
    @Id
    String id;
    String title;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date releaseDate;

    @DBRef(db="flux_classic_games", lazy=false)
    Company company;

    @DBRef(db="flux_classic_games", lazy=false)
    Console console;
}
