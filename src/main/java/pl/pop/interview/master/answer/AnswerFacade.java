package pl.pop.interview.master.answer;

import java.util.List;

public interface AnswerFacade {
    AnswerDTO addNewAnswer(AnswerDTO answerDTO);
    boolean isQuestionAnswered(Long practitionerId, Long questionId);
    List<Answer> getAllAnswers();
}
