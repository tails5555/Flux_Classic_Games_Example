package net.kang.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="person")
public class Person {
    @Id
    String id;
    String name;
    String phoneNumber;

}
