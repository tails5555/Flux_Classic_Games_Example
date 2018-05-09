package net.kang.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableMongoAuditing
@SpringBootApplication
public class FluxExample01Application {

    public static void main(String[] args) {
        SpringApplication.run(FluxExample01Application.class, args);
    }


}
