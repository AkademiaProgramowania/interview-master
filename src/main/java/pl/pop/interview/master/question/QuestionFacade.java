package pl.pop.interview.master.question;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
        if (answered != null && answered) {
            return questionRepository.getQuestionsAnsweredByPractitioner(practitionerId,pageable).map(question -> mapToDto(question));
        } else {
            return questionRepository.getQuestionsUnansweredByPractitioner(practitionerId, pageable).map(question -> mapToDto(question));
        }
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
                .findById( questionId )
                .orElseThrow(
                        () -> new NotFoundException( "Question with ID " + questionId + " does not exist!" )
                );
    }
}
