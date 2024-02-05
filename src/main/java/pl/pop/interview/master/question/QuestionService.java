package pl.pop.interview.master.question;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void addNewQuestion(QuestionDTO questionDTO) {
        Question question = mapToEntity(questionDTO);
        questionRepository.save(question);
    }

    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map( question -> mapToDto(question) )
                .toList();
    }

    private Question mapToEntity(QuestionDTO questionDTO) {
        return new Question(
                questionDTO.getContent(),
                questionDTO.getCorrectAnswer());
    }

    public QuestionDTO mapToDto(Question question) {
        return new QuestionDTO(question.getId(), question.getContent());
    }
}
