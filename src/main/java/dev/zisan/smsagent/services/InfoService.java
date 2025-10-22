package dev.zisan.smsagent.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    private final ChatClient chatClient;

    public InfoService(ChatClient.Builder builder, PgVectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .defaultSystem("You are a helpful assistant that provides information about Zisan based on the provided data. If the user asks a question that is not related to Zisan, politely inform them that you can only provide information about Zisan.")
                .build();
    }

    public String getInfoResponse(String question) {

        return chatClient.prompt().user(question).call().content();
    }
}
