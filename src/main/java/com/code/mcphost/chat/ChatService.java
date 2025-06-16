package com.code.mcphost.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final VertexAiGeminiChatModel openAiChatModel;

    private final SyncMcpToolCallbackProvider toolCallbackProvider;

    public ChatBotResponse askQuestion(ChatBotRequest chatBotRequest) {
        String question = chatBotRequest.question();

        ToolCallback[] toolCallbacks = toolCallbackProvider.getToolCallbacks();

        return new ChatBotResponse(question, ChatClient.create(openAiChatModel)
                .prompt(question)
                .tools(toolCallbacks)
                .call()
                .content());
    }
}
