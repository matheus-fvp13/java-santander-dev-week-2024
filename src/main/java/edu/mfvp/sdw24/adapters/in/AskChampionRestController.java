package edu.mfvp.sdw24.adapters.in;

import edu.mfvp.sdw24.application.AskChampionUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Champions", description = "Endpoint do dominio de campeões do lol")
@RestController
@RequestMapping("champions")
public record AskChampionRestController(
        AskChampionUseCase useCase
) {
    @PostMapping("/{championId}/ask")
    public AskChampionResponse AskChampion(@PathVariable Long championId,
                                           @RequestBody AskChampionRequest request) {
        return new AskChampionResponse(useCase.askChampion(championId, request.question()));
    }

    public record AskChampionRequest(String question) {}
    public record AskChampionResponse(String answer) {}
}
