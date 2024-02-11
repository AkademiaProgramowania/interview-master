package pl.pop.interview.master.answer;

import java.util.List;

public interface AnswerFacade {
    AnswerDTO addNewAnswer(AnswerDTO answerDTO);
    List<Answer> getAllAnswers();
    boolean isQuestionAnswered(Long practitionerId, Long questionId);
}
