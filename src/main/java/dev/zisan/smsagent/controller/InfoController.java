package dev.zisan.smsagent.controller;

import dev.zisan.smsagent.dto.InfoRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("info")
public class InfoController {

    private final ChatClient chatClient;

    public InfoController(ChatClient.Builder builder, PgVectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .defaultSystem("You are a helpful assistant that provides information about Zisan based on the provided data. If the user asks a question that is not related to Zisan, politely inform them that you can only provide information about Zisan.")
                .build();
    }

    @PostMapping("ask")
    public Map<String, String> info(@RequestBody InfoRequest request) {
        return Map.of(
                "response", Objects.requireNonNull(chatClient.prompt()
                        .user(request.getQuestion())
                        .call()
                        .content())
        );
    }
}
