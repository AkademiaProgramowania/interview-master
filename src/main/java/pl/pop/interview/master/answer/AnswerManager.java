package pl.pop.interview.master.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pop.interview.master.question.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AnswerManager implements AnswerFacade {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuestionFacade questionService;

    public QuestionDTO findRandomQuestion() {
        Question found = questionRepository.findRandomQuestion().orElseThrow(() -> new NotFoundException("Question not found"));
        return questionService.mapToDto(found);
    }

    // opcjonalnie
    @Override
    public QuestionDTO generateRandomQuestion() {
        Random random = new Random();
        List<Question> allQuestions = questionRepository.findAll();
        int index = random.nextInt(allQuestions.size());
        Question question = allQuestions.get(index);
        return questionService.mapToDto(question);
    }

    @Override
    public AnswerDTO save(Long questionId, String answer) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("Question not found"));
        // check if the answer is correct, comparing to question
        boolean isCorrect = Objects.equals(answer, question.getCorrectAnswer().toString());
        // new Answer with question content, submitted answer and result
        Answer newAnswer = new Answer(questionId, question.getContent(), answer, isCorrect ? "Correct answer" : "Incorrect answer or answer format YES/NO");
        return AnswerDTO.mapToDto(answerRepository.save(newAnswer));
    }
}
