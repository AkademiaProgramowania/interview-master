package pl.pop.interview.master.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pop.interview.master.util.DataUploader;

@Configuration
public class DataUploaderConfig {

    @Bean
    public CommandLineRunner populateData(DataUploader dataUploader) {
        return args -> {
            dataUploader.populateQuestions();
            dataUploader.populateAccounts();
        };
    }
}
