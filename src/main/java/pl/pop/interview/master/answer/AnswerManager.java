package pl.pop.interview.master.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pop.interview.master.practitioner.PractitionerFacade;
import pl.pop.interview.master.question.*;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class AnswerManager implements AnswerFacade {

    private final AnswerRepository answerRepository;
    private final QuestionFacade questionFacade;
    private final PractitionerFacade practitionerFacade;

    public AnswerDTO addNewAnswer( AnswerDTO answerDTO) {
        // is the question already answered
        if (isQuestionAnswered( answerDTO.getPractitionerId(), answerDTO.getQuestionId() )) {
            throw new RuntimeException(
                    "The Practitioner " + answerDTO.getPractitionerId() + " has already answered this question."
            );
        }

        Question question = questionFacade.getQuestion( answerDTO.getQuestionId() );

        // check if the answer is correct, comparing to question
        boolean isCorrect = Objects.equals(
                answerDTO.isAnswer(),
                question.isCorrectAnswer());

        // new Answer with question content, submitted answer and result
        Answer newAnswer = new Answer(
                question.getContent(),
                answerDTO.isAnswer(),
                isCorrect ? "Correct" : "Incorrect");

        newAnswer.setPractitioner(practitionerFacade.getPractitioner( answerDTO.getPractitionerId() ));
        newAnswer.setQuestion( question );

        return AnswerDTO.mapToDto(answerRepository.save(newAnswer));
    }

    public boolean isQuestionAnswered(Long practitionerId, Long questionId) {
        return answerRepository.isQuestionAnswered(practitionerId, questionId);
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }
}
