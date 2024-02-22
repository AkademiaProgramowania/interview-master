package pl.pop.interview.master.question;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<QuestionDTO> getQuestionsForPractitioner(Long practitionerId, Boolean answered, Pageable pageable) {
        var dtoList = getPagedQuestions(practitionerId, answered, pageable).getContent().stream().map(question -> mapToDto(question)).toList();
        return new PageImpl<>(dtoList, pageable, dtoList.size());
    }

    private Page<Question> getPagedQuestions(Long practitionerId, Boolean answered, Pageable pageable) {
        if (answered) {
            return questionRepository.getQuestionsAnsweredByPractitioner(practitionerId, pageable);
        }
        return questionRepository.getQuestionsUnansweredByPractitioner(practitionerId, pageable);
    }

    private Question mapToEntity(QuestionDTO questionDTO) {
        return new Question(
                questionDTO.getContent(),
                questionDTO.getExpectedAnswer());
    }

    public QuestionDTO mapToDto(Question question) {
        return new QuestionDTO(question.getId(), question.getContent(), question.getExpectedAnswer());
    }

    public QuestionDTO findRandomQuestion() {
        Question found = questionRepository
                .findRandomQuestion()
                .orElseThrow(() -> new NotFoundException("Question not found"));

        return mapToDto(found);
    }

    public Question getQuestion(Long questionId) {
        return questionRepository
                .findById(questionId)
                .orElseThrow(
                        () -> new NotFoundException("Question with ID " + questionId + " does not exist!")
                );
    }
}
