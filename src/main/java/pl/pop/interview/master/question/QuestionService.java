package pl.pop.interview.master.question;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }


    public void addNewQuestion(QuestionDTO questionDTO) {
        Question question = questionMapper.mapDtoToEntity(questionDTO);
        questionRepository.save(question);
    }

    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(question -> questionMapper.mapEntityToDto(question))
                .toList();
    }
}
