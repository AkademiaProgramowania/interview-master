package pl.pop.interview.master.question;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class responsible for initializing data population during application startup.
 */
@Configuration
public class QuestionUploaderConfig {

    /**
     * Creates a CommandLineRunner bean that triggers data population service.
     *
     * @param questionUploader A class responsible for populating data.
     * @return A CommandLineRunner instance.
     */
    @Bean
    public CommandLineRunner populateData(QuestionUploader questionUploader) {
        return args -> {
            questionUploader.populateQuestions();
        };
    }
}
