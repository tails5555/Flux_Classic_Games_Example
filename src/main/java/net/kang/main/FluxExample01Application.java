package net.kang.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.sql.DataSource;

@EnableMongoRepositories(basePackages = "net.kang.repository")
@EntityScan(basePackages = "net.kang.domain")
@ComponentScan(basePackages = "net.kang")
@SpringBootApplication
public class FluxExample01Application {

    public static void main(String[] args) {
        SpringApplication.run(FluxExample01Application.class, args);
    }

}
