package pl.pop.interview.master.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import pl.pop.interview.master.account.AccountDTO;
import pl.pop.interview.master.account.AccountService;
import pl.pop.interview.master.question.QuestionDTO;
import pl.pop.interview.master.question.QuestionService;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@Component
public class DataUploader {
    private static final String DATABASE_UPDATED_QUESTIONS_ADDED = "Database updated - questions added";
    private static final String DATABASE_UPDATED_ACCOUNTS_ADDED = "Database updated = accounts added";
    private QuestionService questionService;
    private AccountService accountService;
    private ObjectMapper objectMapper;

    public void populateQuestions() throws IOException {
        ClassPathResource resource = new ClassPathResource("json/questions.json");
        JsonNode rootNode = objectMapper.readTree(resource.getInputStream());
        JsonNode questionNode = rootNode.get("questions");
        for (JsonNode question : questionNode) {
            QuestionDTO questionDTO = objectMapper.treeToValue(question, QuestionDTO.class);
            questionService.addNewQuestion(questionDTO);
        }
        log.info(DATABASE_UPDATED_QUESTIONS_ADDED);
    }

    public void populateAccounts() throws IOException {
        ClassPathResource resource = new ClassPathResource("json/accounts.json");
        JsonNode rootNode = objectMapper.readTree(resource.getInputStream());
        JsonNode questionNode = rootNode.get("accounts");
        for (JsonNode account : questionNode) {
            AccountDTO accountDTO = objectMapper.treeToValue(account, AccountDTO.class);
            accountService.createNewAccount(accountDTO);
        }
        log.info(DATABASE_UPDATED_ACCOUNTS_ADDED);
    }
}
