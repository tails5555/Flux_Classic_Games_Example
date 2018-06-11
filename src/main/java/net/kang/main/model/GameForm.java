package net.kang.main.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class GameForm {
    String title;
    double price;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date releaseDate;
    String companyId;
    String consoleId;
    String genreId;
}
