package net.kang.main.mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="console")
// 게임 콘솔(오락기) 객체.
public class Console {
    @Id
    String id;

    // 콘솔 이름
    String name;

    // 콘솔 가격. 단위는 달러.
    double price;

    // 콘솔의 비트 수. NES는 8비트, SNES는 16비트 등으로 표현이 가능.
    int bitCount;

    // 콘솔 발매 날짜
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date releaseDate;

    Company company;
}
