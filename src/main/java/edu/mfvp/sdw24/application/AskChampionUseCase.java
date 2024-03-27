package edu.mfvp.sdw24.application;

import edu.mfvp.sdw24.domain.exceptions.ChampionNotFoundException;
import edu.mfvp.sdw24.domain.ports.ChampionRepository;


public record AskChampionUseCase(ChampionRepository repository) {
    public String askChampion(Long championId, String question) {
        var champion = repository.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));
        return champion.generateContextByQuestion(question);
    }
}
