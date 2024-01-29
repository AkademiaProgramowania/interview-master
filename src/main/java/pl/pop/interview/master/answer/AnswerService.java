package pl.pop.interview.master.answer;

import org.springframework.stereotype.Service;
import pl.pop.interview.master.question.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository, QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.questionService = questionService;
    }

    public QuestionDTO generateRandomQuestion() {
        Random random = new Random();
        List<Question> allQuestions = questionRepository.findAll();
        int index = random.nextInt(allQuestions.size());
        Question question = allQuestions.get(index);
        return questionService.mapToDto(question);
    }

    // opcjonalnie
    public QuestionDTO findRandomQuestion() {
        Question found = questionRepository.findRandomQuestion().orElseThrow(()-> new QuestionServiceException ("Question not found"));
        return questionService.mapToDto(found);
    }

    public AnswerDTO saveNewAnswer(Long id, String answer) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionServiceException("Question not found"));
        // check if the answer is correct, comparing to question
        boolean isCorrect = Objects.equals(answer, question.getCorrectAnswer().toString());
        // new Answer with question content, submitted answer and result
        // dopóki nie ma połączonych tabel, treść pytania jest widoczna w Answer na potrzeby podglądu w konsoli h2
        Answer newAnswer = new Answer(question.getContent(), answer, isCorrect ? "Correct answer" : "Incorrect answer or answer format YES/NO");
        return mapToDto(answerRepository.save(newAnswer));
    }

    private AnswerDTO mapToDto(Answer answer) {
        return new AnswerDTO(answer.getQuestion(), answer.getAnswer(), answer.getResult());
    }

}
