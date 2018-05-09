package net.kang.main.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Document(collection="game")
// 고전 게임 객체
public class Game {
    @Id
    String id;

    // 게임 이름
    String title;

    // 게임 가격. 단위는 달러.
    double price;

    // 게임 발매 날짜
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date releaseDate;

    // 게임 제작 회사
    @DBRef(db="flux_classic_games", lazy=false)
    Company company;

    // 게임이 작동 가능한 콘솔
    @DBRef(db="flux_classic_games", lazy=false)
    Console console;

    // 게임 장르
    @DBRef(db="flux_classic_games", lazy=false)
    Genre genre;
}
