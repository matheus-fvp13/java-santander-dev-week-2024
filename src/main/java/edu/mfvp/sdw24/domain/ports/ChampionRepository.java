package edu.mfvp.sdw24.domain.ports;

import edu.mfvp.sdw24.domain.model.Champion;

import java.util.List;
import java.util.Optional;

public interface ChampionRepository {
    List<Champion> findAll();
    Optional<Champion> findById(Long id);
}