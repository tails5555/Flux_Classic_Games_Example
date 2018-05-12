package net.kang.main.mongodb.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
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
    Company company;

    // 게임이 작동 가능한 콘솔(게임 기기)
    Console console;

    // 게임 장르
    Genre genre;
}
