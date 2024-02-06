package pl.pop.interview.master.question;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Class responsible for populating questions from a JSON file into the database.
 */
@Slf4j
@Component
public class QuestionUploader {
    private static final String DATABASE_UPDATED_QUESTIONS_ADDED = "Database updated - questions added";
    private final ObjectMapper objectMapper;
    private final QuestionService questionService;

    public QuestionUploader(ObjectMapper objectMapper, QuestionService questionService) {
        this.objectMapper = objectMapper;
        this.questionService = questionService;
    }

    /**
     * Populates question data into the database from a JSON file.
     *
     * @throws IOException If there is an issue reading the JSON file.
     */
    public void populateQuestions() throws IOException {
        ClassPathResource resource = new ClassPathResource("json/questions.json");
        JsonNode rootNode = objectMapper.readTree(resource.getInputStream());
        JsonNode questionNode = rootNode.get("questions");
        for (JsonNode question: questionNode) {
            QuestionDTO questionDTO = objectMapper.treeToValue(question, QuestionDTO.class);
            questionService.addNewQuestion(questionDTO);
        }
        log.info(DATABASE_UPDATED_QUESTIONS_ADDED);
    }

}
