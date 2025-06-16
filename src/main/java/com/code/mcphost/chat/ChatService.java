package com.code.mcphost.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final VertexAiGeminiChatModel openAiChatModel;

    private final SyncMcpToolCallbackProvider toolCallbackProvider;

    public ChatBotResponse askQuestion(ChatBotRequest chatBotRequest) {
        String question = chatBotRequest.question();

        ToolCallback[] toolCallbacks = toolCallbackProvider.getToolCallbacks();

        ChatClient chatClient = ChatClient.builder(openAiChatModel)
                .defaultToolCallbacks(toolCallbacks)
                .build();


        return new ChatBotResponse(question, chatClient
                .prompt(question)
                .system("Just return the answer as text not as markdown or json")
                .call()
                .content());
    }
}
