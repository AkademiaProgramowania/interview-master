package pl.pop.interview.master.answer;

import pl.pop.interview.master.question.QuestionDTO;

public interface AnswerFacade {
    QuestionDTO findRandomQuestion();
    QuestionDTO generateRandomQuestion();
    AnswerDTO save(Long questionId, String answer);
}
