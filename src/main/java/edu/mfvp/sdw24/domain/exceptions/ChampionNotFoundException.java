package edu.mfvp.sdw24.domain.exceptions;

public class ChampionNotFoundException extends  RuntimeException {
    public ChampionNotFoundException(Long championId) {
        super("Champion %d not found.".formatted(championId));
    }
}
