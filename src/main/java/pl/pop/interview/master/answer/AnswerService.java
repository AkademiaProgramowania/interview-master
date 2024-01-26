package pl.pop.interview.master.answer;

import org.springframework.stereotype.Service;
import pl.pop.interview.master.question.Question;
import pl.pop.interview.master.question.QuestionRepository;
import pl.pop.interview.master.question.QuestionServiceException;

import java.util.List;
import java.util.Random;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public Question generateRandomQuestion() { // todo zamienić zwracany typ na QuestionDTO, dodać mappery
        Random random = new Random();
        List<Question> allQuestions = questionRepository.findAll();
        int index = random.nextInt(allQuestions.size());
        return allQuestions.get(index);
    }

    public AnswerDTO saveNewAnswer(Long id, String answer) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionServiceException("Question not found"));
        // check if the answer is correct, comparing to question
        boolean isCorrect = answer.equals(question.getCorrectAnswer().toString());
        // new Answer with question content, submitted answer and result
        Answer newAnswer = new Answer(question.getContent(), answer, isCorrect ? "Correct answer" : "Incorrect answer or answer format YES/NO");
        answerRepository.save(newAnswer);
        return mapToDto(newAnswer);
    }

    private AnswerDTO mapToDto(Answer answer) {
        return new AnswerDTO(answer.getQuestion(), answer.getAnswer(), answer.getResult());
    }

    private Answer mapToEntity(AnswerDTO answerDTO) {
        return new Answer(answerDTO.getQuestion(), answerDTO.getAnswer(), answerDTO.getResult());
    }

}
