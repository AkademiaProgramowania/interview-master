package pl.pop.interview.master.question;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @PostConstruct
    private void createStartQuestionAndPutToDB() {
        if (questionRepository.count() == 0) {
            questionRepository.saveAll(createQuestion());
        }
    }

    private List createQuestion(){
        ArrayList<Question> questionArrayList = new ArrayList<>();
        questionArrayList.add(new Question("Czy w Javie istnieje wielokrotne dziedziczenie klas?", YesNo.NO));
        questionArrayList.add(new Question("Czy można utworzyć obiekt klasy abstrakcyjnej w Javie?", YesNo.NO));
        questionArrayList.add(new Question("Czy w Javie istnieje automatyczne zbieranie śmieci", YesNo.YES));
        questionArrayList.add(new Question("Czy interfejs w Javie może zawierać implementacje metod?", YesNo.YES));
        questionArrayList.add(new Question("Czy w Javie można nadpisywać metody statyczne?", YesNo.NO));
        questionArrayList.add(new Question("Czy Javę można używać do programowania na platformie Android?", YesNo.YES));
        questionArrayList.add(new Question("Czy Javie można przekazywać argumenty do metody przez referencję?", YesNo.NO));
        questionArrayList.add(new Question("Czy w Javie można obsługiwać wielowątkowość?", YesNo.YES));
        questionArrayList.add(new Question("Czy w Javie można dziedziczyć po więcej niż jednej klasie?", YesNo.NO));
        questionArrayList.add(new Question("CCzy Javie można używać do tworzenia aplikacji webowych?", YesNo.YES));
        return questionArrayList;
    }

    public void addNewQuestion(QuestionDTO questionDTO) {
        Question question = buildQuestionFromDTO(questionDTO);
        questionRepository.save(question);
    }

    private Question buildQuestionFromDTO(QuestionDTO questionDTO) {
        return new Question(
                questionDTO.getContent(),
                questionDTO.getCorrectAnswer()
        );
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }


}
