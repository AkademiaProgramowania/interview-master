package pl.pop.interview.master.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pop.interview.master.question.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AnswerFacade {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuestionFacade questionFacade;

    public QuestionDTO findRandomQuestion() {
        Question found = questionRepository.findRandomQuestion().orElseThrow(() -> new NotFoundException("Question not found"));
        return questionFacade.mapToDto(found);
    }

    // opcjonalnie
    public QuestionDTO generateRandomQuestion() {
        Random random = new Random();
        List<Question> allQuestions = questionRepository.findAll();
        int index = random.nextInt(allQuestions.size());
        Question question = allQuestions.get(index);
        return questionFacade.mapToDto(question);
    }

    public AnswerDTO save(Long questionId, boolean isTrue) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("Question not found"));
        // check if the answer is correct, comparing to question
        boolean isCorrect = Objects.equals(isTrue, question.isCorrectAnswer());
        // new Answer with question content, submitted answer and result
        Answer newAnswer = new Answer(questionId, question.getContent(), isTrue, isCorrect ? "Correct answer" : "Incorrect answer or answer format YES/NO");
        return AnswerDTO.mapToDto(answerRepository.save(newAnswer));
    }
}
