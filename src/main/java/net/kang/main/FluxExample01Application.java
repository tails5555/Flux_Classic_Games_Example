package net.kang.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="net.kang.main.mysql_repository")
@SpringBootApplication
public class FluxExample01Application {

    public static void main(String[] args) {
        SpringApplication.run(FluxExample01Application.class, args);
    }


}
