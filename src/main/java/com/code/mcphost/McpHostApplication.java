package com.code.mcphost;

import com.code.mcphost.tools.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class McpHostApplication {

//    private final Weather weather;

    public static void main(String[] args) {
        SpringApplication.run(McpHostApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider weatherTools(Weather weatherService) {
        return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
    }

}
