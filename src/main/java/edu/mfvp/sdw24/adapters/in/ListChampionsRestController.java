package edu.mfvp.sdw24.adapters.in;

import edu.mfvp.sdw24.application.ListChampionsUseCase;
import edu.mfvp.sdw24.domain.model.Champion;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Champions", description = "Endpoint do dominio de campeões do lol")
@RestController
@RequestMapping("champions")
public record ListChampionsRestController(
        ListChampionsUseCase useCase
) {
    @GetMapping
    public List<Champion> findAllChampions() {
        return useCase.findAll();
    }
}
