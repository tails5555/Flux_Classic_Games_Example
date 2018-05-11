package net.kang.main.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="genre")
// 장르 객체
public class Genre {
    @Id
    String id;

    // 장르 이름
    String name;
}
