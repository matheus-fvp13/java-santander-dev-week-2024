package edu.mfvp.sdw24.application;

import edu.mfvp.sdw24.domain.model.Champion;
import edu.mfvp.sdw24.domain.ports.ChampionRepository;

import java.util.List;

public record ListChampionsUseCase(ChampionRepository repository) {
    public List<Champion> findAll() {
        return repository.findAll();
    }
}
