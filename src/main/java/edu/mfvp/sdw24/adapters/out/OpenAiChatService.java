package edu.mfvp.sdw24.adapters.out;

import edu.mfvp.sdw24.domain.ports.GenerativeAiService;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "OpenAiChatApi", url = "${openai.base-url}", configuration = OpenAiChatService.Config.class)
public interface OpenAiChatService extends GenerativeAiService {
    @PostMapping("/v1/chat/completions")
    OpenAiChatCompletionResponse chatCompletion(OpenAiChatCompletionRequest request);

    @Override
    default String generateContent(String objective, String context) {
        String model = "gpt-3.5-turbo";
        List<Message> messages = List.of(
                new Message("system", objective),
                new Message("user", context)
        );

        var request = new OpenAiChatCompletionRequest(model, messages);
        var response = chatCompletion(request);

        return response.choices.getFirst().message().content;
    }

    record OpenAiChatCompletionRequest(String model, List<Message> messages) { }
    record  Message(String role, String content) { }

    record OpenAiChatCompletionResponse(List<Choice> choices) { }
    record Choice(Message message) {}

    class Config {
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${openai.api-key}") String apiKey) {
            return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
        }
    }
}
