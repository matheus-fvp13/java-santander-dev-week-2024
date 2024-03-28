package edu.mfvp.sdw24.adapters.out;

import edu.mfvp.sdw24.domain.ports.GenerativeAiService;
import feign.FeignException;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "GEMINI", matchIfMissing = true)
@FeignClient(name = "geminiApi", url = "${gemini.base-url}", configuration = GoogleGeminiService.Config.class)
public interface GoogleGeminiService extends GenerativeAiService {
    @PostMapping("/v1beta/models/gemini-pro:generateContent")
    GoogleGeminiResponse textOnlyInput(GoogleGeminiRequest request);

    @Override
    default String generateContent(String objective, String context) {
        var prompt = """
            %s
            %s
            """.formatted(objective, context);
        var request = new GoogleGeminiRequest(
                List.of(new Content(List.of(new Part(prompt))))
        );
        try {
            return textOnlyInput(request).candidates().getFirst().content().parts().getFirst().text();
        }catch (FeignException httpError) {
            return "The HTTP request to Gemini API failed.";
        }catch (Exception unexpectedError) {
            return "The Gemini API return don't have the expected data.";
        }

    }

    record GoogleGeminiRequest(List<Content> contents) { }
    record Content(List<Part> parts) { }
    record Part(String text) { }
    record GoogleGeminiResponse(List<Candidate> candidates) { }
    record Candidate(Content content) { }

    class Config {
        @Bean
        RequestInterceptor apiKeyRequestInterceptor(@Value("${gemini.api-key}") String apiKey) {
            return requestTemplate -> requestTemplate.query("key", apiKey);
        }
    }
}
