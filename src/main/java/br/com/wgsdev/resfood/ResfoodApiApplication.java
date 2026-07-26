package br.com.wgsdev.resfood;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.wgsdev.resfood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class ResfoodApiApplication {

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    SpringApplication.run(ResfoodApiApplication.class, args);
  }

}
