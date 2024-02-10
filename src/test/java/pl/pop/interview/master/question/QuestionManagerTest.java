package pl.pop.interview.master.question;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith( MockitoExtension.class )
public class QuestionManagerTest {
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionManager questionManager;

    @Test
    public void testAddNewQuestion_SuccessfulAddedQuestion() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setContent("Is it ok?");
        questionDTO.setYesNo(true);
        questionDTO.setCorrectAnswer(true);

        questionManager.addNewQuestion( questionDTO );

        // capture Question object for test private method buildQuestionFromDTO( QuestionDTO questionDTO )
        ArgumentCaptor<Question> questionCaptor = ArgumentCaptor.forClass( Question.class );

        verify( questionRepository ).save( questionCaptor.capture() );

        Question capturedQuestion = questionCaptor.getValue();

        assertEquals( questionDTO.getContent(), capturedQuestion.getContent() );
        assertEquals( questionDTO.isYesNo(), capturedQuestion.isYesNo() );
        assertEquals( questionDTO.isCorrectAnswer(), capturedQuestion.isCorrectAnswer() );
    }

    @Test
    public void testGetAllQuestions_SuccessfulReturnedQuestions() {
        List<Question> expectedQuestions = Arrays.asList(
                new Question( "Is it ok?", true),
                new Question( "Is it bad?", false)
        );

        // create an imitation of a repository that will return a expectedQuestions list
        when( questionRepository.findAll() ).thenReturn( expectedQuestions );

        List<QuestionDTO> actualQuestionsDTO = questionManager.getAllQuestions();

        // we need to cast DTO to Question, cause service returns List<QuestionDTO>
        List<Question> actualQuestions = actualQuestionsDTO.stream()
                .map(questionDTO -> new Question(questionDTO.getContent(), questionDTO.isCorrectAnswer()))
                .map(questionDTO -> new Question(questionDTO.getContent(), questionDTO.isYesNo()))
                .toList();

        assertEquals( expectedQuestions.size(), actualQuestions.size() );
        assertAll(
                () -> assertEquals(expectedQuestions.get(0).getContent(), actualQuestions.get(0).getContent()),
                () -> assertEquals(expectedQuestions.get(0).isYesNo(), actualQuestions.get(0).isYesNo()),
                () -> assertEquals(expectedQuestions.get(0).isCorrectAnswer(), actualQuestions.get(0).isCorrectAnswer()),
                () -> assertEquals(expectedQuestions.get(1).getContent(), actualQuestions.get(1).getContent()),
                () -> assertEquals(expectedQuestions.get(1).isYesNo(), actualQuestions.get(1).isYesNo())
                () -> assertEquals(expectedQuestions.get(1).isCorrectAnswer(), actualQuestions.get(1).isCorrectAnswer())
        );
    }
}
