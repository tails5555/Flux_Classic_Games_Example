package net.kang.main.mongodb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kang.main.mongodb.domain.Genre;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreVO {
    String id;
    String name;
    public static GenreVO builtToGenreVO(Genre genre){
        return new GenreVO(genre.getId(), genre.getName());
    }
    public static Genre builtToGenre(GenreVO genreVO){
        return new Genre(genreVO.getId(), genreVO.getName());
    }
}
