package pl.pop.interview.master.answer;

import org.springframework.stereotype.Service;
import pl.pop.interview.master.practitioner.Practitioner;
import pl.pop.interview.master.practitioner.PractitionerService;
import pl.pop.interview.master.practitioner.PractitionerServiceException;
import pl.pop.interview.master.question.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Service class for managing Answer object
 */
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    private final PractitionerService practitionerService;

    public AnswerService( AnswerRepository answerRepository,
                          QuestionRepository questionRepository,
                          QuestionService questionService,
                          PractitionerService practitionerService ) {

        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.questionService = questionService;
        this.practitionerService = practitionerService;
    }

    public QuestionDTO findRandomQuestion() {
        Question found = questionRepository
                .findRandomQuestion()
                .orElseThrow(()-> new NotFoundException("Question not found"));

        return questionService.mapToDto(found);
    }

    // opcjonalnie
    public QuestionDTO generateRandomQuestion() {
        Random random = new Random();
        List<Question> allQuestions = questionRepository.findAll();
        int index = random.nextInt(allQuestions.size());
        Question question = allQuestions.get(index);
        return questionService.mapToDto(question);
    }

    public AnswerDTO addNewAnswer( AnswerDTO answerDTO) {
        // is the question already answered
        if (isAnsweredByPractitioner( answerDTO.getPractitionerId(), answerDTO.getQuestionId() )) {
            throw new PractitionerServiceException(
                    "The Practitioner " + answerDTO.getPractitionerId() + " has already answered this question."
            );
        }

        Question question = questionRepository
                .findById( answerDTO.getQuestionId() )
                .orElseThrow(() -> new NotFoundException("Question not found"));

        // check if the answer is correct, comparing to question
        boolean isCorrect = Objects.equals(answerDTO.getAnswer(), question.getCorrectAnswer().toString());

        // new Answer with question content, submitted answer and result
        Answer newAnswer = new Answer(
                question.getContent(),
                answerDTO.getAnswer(),
                isCorrect ? "Correct answer" : "Incorrect answer or answer format YES/NO");

        newAnswer.setPractitioner(practitionerService.getPractitioner( answerDTO.getPractitionerId() ));
        newAnswer.setQuestion( question );

        return AnswerDTO.mapToDto(answerRepository.save(newAnswer));
    }

    // additional method for check is answer is answered by practitioner
    private boolean isAnsweredByPractitioner(Long practitionerId, Long questionId) {
        Practitioner practitioner = practitionerService.getPractitioner( practitionerId );
        List<Answer> answers = practitioner.getAnswers();

        return answers.stream().anyMatch( answer -> answer.getQuestion().getId().equals( questionId ) );
    }
}
