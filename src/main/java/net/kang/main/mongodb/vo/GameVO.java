package net.kang.main.mongodb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kang.main.mongodb.domain.Company;
import net.kang.main.mongodb.domain.Console;
import net.kang.main.mongodb.domain.Game;
import net.kang.main.mongodb.domain.Genre;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameVO {
    String id;
    String title;
    double price;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date releaseDate;

    String companyName;
    String companyAddress;

    String consoleName;
    int consoleBitCount;

    String genreName;

    public static GameVO builtToGameVO(Game game, Company company, Console console, Genre genre){
        return new GameVO(game.getId(), game.getTitle(), game.getPrice(), game.getReleaseDate(), company.getName(), company.getAddress(), console.getName(), console.getBitCount(), genre.getName());
    }
}
