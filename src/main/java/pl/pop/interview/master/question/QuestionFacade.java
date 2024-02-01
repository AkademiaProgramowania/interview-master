package pl.pop.interview.master.question;

import java.util.List;
/**
 * The {@code QuestionFacade} interface defines high-level operations for managing interview questions.
 * This facade provides methods to add new questions and retrieve information about existing questions.
 */
public interface QuestionFacade {
    void addNewQuestion(QuestionDTO questionDTO);
    List<QuestionDTO> getAllQuestions();
    QuestionDTO mapToDto(Question question);
    QuestionDTO findRandomQuestion();
    QuestionDTO generateRandomQuestion();
    Question getQuestion(Long questionId);
}
