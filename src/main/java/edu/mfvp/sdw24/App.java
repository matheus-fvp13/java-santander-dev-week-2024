package edu.mfvp.sdw24;

import edu.mfvp.sdw24.application.AskChampionUseCase;
import edu.mfvp.sdw24.application.ListChampionsUseCase;
import edu.mfvp.sdw24.domain.ports.ChampionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    ListChampionsUseCase provideListChampionsUseCase(ChampionRepository championRepository) {
        return new ListChampionsUseCase(championRepository);
    }

    @Bean
    AskChampionUseCase provideAskChampionUseCase(ChampionRepository championRepository) {
        return new AskChampionUseCase(championRepository);
    }

}
