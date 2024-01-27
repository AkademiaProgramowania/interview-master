package pl.pop.interview.master.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class responsible for mapping during application startup.
 */
@Configuration
public class MapperConfig {

    /**
     * Creates an ObjectMapper Bean.
     *
     * @return A ObjectMapper instance.
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
