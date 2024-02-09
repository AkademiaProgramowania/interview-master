package pl.pop.interview.master.answer;

import pl.pop.interview.master.question.QuestionDTO;

public interface AnswerFacade {
    AnswerDTO addNewAnswer(AnswerDTO answerDTO);
    boolean isQuestionAnswered(Long practitionerId, Long questionId);
}
