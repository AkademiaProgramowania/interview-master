package pl.pop.interview.master.question;

import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
class QuestionManager implements QuestionFacade{
    private final QuestionRepository questionRepository;

    @Override
    public void addNewQuestion(QuestionDTO questionDTO) {
        Question question = mapToEntity(questionDTO);
        questionRepository.save(question);
    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map( question -> mapToDto(question) )
                .toList();
    }

    private Question mapToEntity(QuestionDTO questionDTO) {
        return new Question(
                questionDTO.getContent(),
                questionDTO.isCorrectAnswer());
    }

    public QuestionDTO mapToDto(Question question) {
        return new QuestionDTO(question.getId(), question.getContent(), question.isCorrectAnswer());
        return new QuestionDTO(question.getId(), question.getContent(), question.isYesNo());
    }

    public QuestionDTO findRandomQuestion() {
        Question found = questionRepository.findRandomQuestion().orElseThrow(() -> new NotFoundException("Question not found"));
        return mapToDto(found);
    }

    @Override
    public QuestionDTO generateRandomQuestion() {
        Random random = new Random();
        List<Question> allQuestions = questionRepository.findAll();
        int index = random.nextInt(allQuestions.size());
        Question question = allQuestions.get(index);
        return mapToDto(question);
    }

    public Question getQuestion(Long questionId) {

        return questionRepository
                .findById( questionId )
                .orElseThrow(
                        () -> new NotFoundException( "Question with ID " + questionId + " does not exist!" )
        );
    }
}
