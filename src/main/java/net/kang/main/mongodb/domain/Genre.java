package net.kang.main.mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="genre")
// 장르 객체
public class Genre {
    @Id
    String id;

    // 장르 이름
    String name;
}
