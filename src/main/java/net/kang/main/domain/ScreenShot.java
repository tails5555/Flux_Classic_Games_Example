package net.kang.main.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="screenshot")
public class ScreenShot {
    @Id
    String id;
    int idx_id;
}
