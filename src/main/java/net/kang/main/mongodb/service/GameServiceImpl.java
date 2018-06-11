package net.kang.main.mongodb.service;

import net.kang.main.model.GameForm;
import net.kang.main.mongodb.domain.Company;
import net.kang.main.mongodb.domain.Console;
import net.kang.main.mongodb.domain.Game;
import net.kang.main.mongodb.domain.Genre;
import net.kang.main.mongodb.repository.CompanyRepository;
import net.kang.main.mongodb.repository.ConsoleRepository;
import net.kang.main.mongodb.repository.GameRepository;
import net.kang.main.mongodb.repository.GenreRepository;
import net.kang.main.mongodb.vo.GameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GameServiceImpl implements GameService{
    @Autowired GameRepository gameRepository;
    @Autowired CompanyRepository companyRepository;
    @Autowired ConsoleRepository consoleRepository;
    @Autowired GenreRepository genreRepository;

    // Reactive MongoDB에서 DBRef를 제공하지 않아서 데이터의 무결성을 확립하기 위해 이를 따로 작성하였다.
    private GameVO integrityConfirm(Game game){
        if (game.getCompany() != null && game.getConsole() != null && game.getGenre() != null) {
            Company curCompany = game.getCompany();
            Company newCompany = companyRepository.findById(curCompany.getId())
                    .defaultIfEmpty( new Company() )
                    .block();

            Console curConsole = game.getConsole();
            Console newConsole = consoleRepository.findById(curConsole.getId())
                    .defaultIfEmpty( new Console() )
                    .block();

            Genre curGenre = game.getGenre();
            Genre newGenre = genreRepository.findById(curGenre.getId())
                    .defaultIfEmpty( new Genre() )
                    .block();

            if(!curCompany.equals(newCompany) || !curConsole.equals(newConsole) || !curGenre.equals(newGenre)) {
                game.setCompany(newCompany);
                game.setConsole(newConsole);
                game.setGenre(newGenre);
                return gameRepository.save(game)
                        .map(g -> GameVO.builtToGameVO(g, newCompany, newConsole, newGenre)).block();
            }
        }
        return GameVO.builtToGameVO(game, game.getCompany(), game.getConsole(), game.getGenre());
    }

    @Override
    public Flux<GameVO> findAll(){
        return gameRepository.findAll().map(game -> integrityConfirm(game));
    }

    @Override
    public Mono<GameVO> findOne(String id){
        return gameRepository.findById(id).map(game -> integrityConfirm(game));
    }

    @Override
    public Mono<GameVO> create(GameForm gameForm){
        Game game = new Game();
        Company company = companyRepository.findById(gameForm.getCompanyId())
                .defaultIfEmpty( new Company() )
                .block();

        Console console = consoleRepository.findById(gameForm.getConsoleId())
                .defaultIfEmpty( new Console() )
                .block();

        Genre genre = genreRepository.findById(gameForm.getGenreId())
                .defaultIfEmpty( new Genre() )
                .block();

        game.setTitle(gameForm.getTitle());
        game.setPrice(gameForm.getPrice());
        game.setReleaseDate(gameForm.getReleaseDate());

        if(!company.equals(new Company()))
            game.setCompany(company);
        if(!console.equals(new Console()))
            game.setConsole(console);
        if(!genre.equals(new Genre()))
            game.setGenre(genre);

        return gameRepository.insert(game).map(g -> GameVO.builtToGameVO(g, company, console, genre));
    }

    @Override
    public Mono<GameVO> update(String id, GameForm gameForm){
        Company company = companyRepository.findById(gameForm.getCompanyId())
                .defaultIfEmpty( new Company() )
                .block();

        Console console = consoleRepository.findById(gameForm.getConsoleId())
                .defaultIfEmpty( new Console() )
                .block();

        Genre genre = genreRepository.findById(gameForm.getGenreId())
                .defaultIfEmpty( new Genre() )
                .block();

        return gameRepository.findById(id)
                .flatMap(g -> {
                    g.setTitle(gameForm.getTitle());
                    g.setPrice(gameForm.getPrice());
                    g.setReleaseDate(gameForm.getReleaseDate());
                    if(!company.equals(new Company()))
                        g.setCompany(company);
                    if(!console.equals(new Console()))
                        g.setConsole(console);
                    if(!genre.equals(new Genre()))
                        g.setGenre(genre);
                    return gameRepository.save(g).map(game -> GameVO.builtToGameVO(game, company, console, genre));
                });
    }

    @Override
    public Mono<Void> delete(String id){
        return gameRepository.findById(id)
                .flatMap(g -> {
                    return gameRepository.delete(g);
                });
    }

}
