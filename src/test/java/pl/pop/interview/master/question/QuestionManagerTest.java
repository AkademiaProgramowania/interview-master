package pl.pop.interview.master.question;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith( MockitoExtension.class )
class QuestionManagerTest {
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionManager questionManager;

    @Test
    public void testAddNewQuestion_SuccessfulAddedQuestion() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setContent("Is it ok?");
        questionDTO.setCorrectAnswer(true);
        questionDTO.setCorrectAnswer(true);

        questionManager.addNewQuestion( questionDTO );

        // capture Question object for test private method buildQuestionFromDTO( QuestionDTO questionDTO )
        ArgumentCaptor<Question> questionCaptor = ArgumentCaptor.forClass( Question.class );

        verify( questionRepository ).save( questionCaptor.capture() );

        Question capturedQuestion = questionCaptor.getValue();

        assertEquals( questionDTO.getContent(), capturedQuestion.getContent() );
        assertEquals( questionDTO.isCorrectAnswer(), capturedQuestion.isCorrectAnswer() );
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
                .map(questionDTO -> new Question(questionDTO.getContent(), questionDTO.isCorrectAnswer()))
                .toList();

        assertEquals( expectedQuestions.size(), actualQuestions.size() );
        assertAll(
                () -> assertEquals(expectedQuestions.get(0).getContent(), actualQuestions.get(0).getContent()),
                () -> assertEquals(expectedQuestions.get(0).isCorrectAnswer(), actualQuestions.get(0).isCorrectAnswer()),
                () -> assertEquals(expectedQuestions.get(0).isCorrectAnswer(), actualQuestions.get(0).isCorrectAnswer())
        );
    }

    @Test
    public void testFindRandomQuestion() {
        Question question = new Question("content", true);
        QuestionDTO questionDTO = new QuestionDTO("content", true);
        QuestionDTO questionDTO2 = new QuestionDTO("no content", false);

        when(questionRepository.findRandomQuestion()).thenReturn( Optional.of( question ) );

        questionRepository.save(question);

        assertEquals( questionDTO.getContent(), questionManager.findRandomQuestion().getContent() );
        assertNotEquals(questionDTO2.getContent(), questionManager.findRandomQuestion().getContent());
    }
}
