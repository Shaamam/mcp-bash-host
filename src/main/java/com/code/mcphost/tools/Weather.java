package com.code.mcphost.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Weather {

    @Tool(description = "Fetches the current weather for a given location")
    public Map<String,String> getWeather(String location) {
        // Simulate fetching weather data
        return Map.of(
            "location", location,
            "temperature", "22°C",
            "condition", "Sunny"
        );
    }
}
