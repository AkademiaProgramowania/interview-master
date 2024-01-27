package pl.pop.interview.master.question;

import java.util.List;

public interface QuestionFacade {
    void addNewQuestion(QuestionDTO questionDTO);
    List<QuestionDTO> getAllQuestions();
}
