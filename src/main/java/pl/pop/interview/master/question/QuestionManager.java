package pl.pop.interview.master.question;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class QuestionManager implements QuestionFacade{
    private final QuestionRepository questionRepository;

    @PostConstruct
    private void saveBasicQuestions() {
        if (questionRepository.count() == 0) {
            questionRepository.saveAll(createBasicQuestions());
        }
    }

    private List<Question> createBasicQuestions(){
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Czy w Javie istnieje wielokrotne dziedziczenie klas?", YesNo.NO));
        questions.add(new Question("Czy można utworzyć obiekt klasy abstrakcyjnej w Javie?", YesNo.NO));
        questions.add(new Question("Czy w Javie istnieje automatyczne zbieranie śmieci", YesNo.YES));
        questions.add(new Question("Czy interfejs w Javie może zawierać implementacje metod?", YesNo.YES));
        questions.add(new Question("Czy w Javie można nadpisywać metody statyczne?", YesNo.NO));
        questions.add(new Question("Czy Javę można używać do programowania na platformie Android?", YesNo.YES));
        questions.add(new Question("Czy Javie można przekazywać argumenty do metody przez referencję?", YesNo.NO));
        questions.add(new Question("Czy w Javie można obsługiwać wielowątkowość?", YesNo.YES));
        questions.add(new Question("Czy w Javie można dziedziczyć po więcej niż jednej klasie?", YesNo.NO));
        questions.add(new Question("Czy Javie można używać do tworzenia aplikacji webowych?", YesNo.YES));
        return questions;
    }
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
                questionDTO.getCorrectAnswer());
    }

    public QuestionDTO mapToDto(Question question) {
        return new QuestionDTO(question.getId(), question.getContent());
    }
}
