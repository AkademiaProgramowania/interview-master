package pl.pop.interview.master.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionFacade {
    private final QuestionRepository questionRepository;


    public void addNewQuestion(QuestionDTO questionDTO) {
        Question question = mapToEntity(questionDTO);
        questionRepository.save(question);
    }


    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(question -> mapToDto(question))
                .toList();
    }

    private Question mapToEntity(QuestionDTO questionDTO) {
        return new Question(
                questionDTO.getContent(),
                questionDTO.isCorrectAnswer());
    }

    public QuestionDTO mapToDto(Question question) {
        return new QuestionDTO(question.getId(), question.getContent(), question.isCorrectAnswer());
    }
}
