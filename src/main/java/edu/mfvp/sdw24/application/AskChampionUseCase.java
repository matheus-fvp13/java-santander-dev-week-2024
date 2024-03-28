package edu.mfvp.sdw24.application;

import edu.mfvp.sdw24.domain.exceptions.ChampionNotFoundException;
import edu.mfvp.sdw24.domain.ports.ChampionRepository;
import edu.mfvp.sdw24.domain.ports.GenerativeAiService;


public record AskChampionUseCase(ChampionRepository repository, GenerativeAiService generativeAi) {
    public String askChampion(Long championId, String question) {
        var champion = repository.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));
        var context  = champion.generateContextByQuestion(question);
        var objective = """
            Atue como um assistente com a habilidade de se comportar como os campeões do League of Legends (LOL)
            Responda perguntas incorporando a personalidade e estilo de um determinado campeão.
            Segue a pergunta, o nome do campeão e seua respectiva lore (história):
            
            """;

        return generativeAi.generateContent(objective, context);
    }
}
