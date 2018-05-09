package net.kang.main.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;

@Data
@Document(collection="company")
// 고전 게임 제작 회사. 물론 콘솔 게임 회사도 이용할 수 있다.
public class Company {
    @Id
    String id;

    // 회사 이름
    String name;

    // 회사 주소
    String address;

    // 회사 창립일
    @DateTimeFormat(iso = ISO.DATE_TIME)
    Date foundDate;
}
