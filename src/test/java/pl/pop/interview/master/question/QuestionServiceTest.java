package pl.pop.interview.master.question;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith( MockitoJUnitRunner.class )
public class QuestionServiceTest {
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionService questionService;

    @Test
    public void testAddNewQuestion_SuccessfulAddedQuestion() {
        QuestionDTO questionDTO = new QuestionDTO( "Is it ok?", YesNo.YES );

        questionService.addNewQuestion( questionDTO );

        // capture Question object for test private method buildQuestionFromDTO( QuestionDTO questionDTO )
        ArgumentCaptor<Question> questionCaptor = ArgumentCaptor.forClass( Question.class );

        verify( questionRepository ).save( questionCaptor.capture() );

        Question capturedQuestion = questionCaptor.getValue();

        assertEquals( questionDTO.getContent(), capturedQuestion.getContent() );
        assertEquals( questionDTO.getCorrectAnswer(), capturedQuestion.getCorrectAnswer() );
    }

    @Test
    public void testGetAllQuestions_SuccessfulReturnedQuestions() {
        List<Question> expectedQuestions = Arrays.asList(
                new Question( "Is it ok?", YesNo.YES ),
                new Question( "Is it bad?", YesNo.NO )
        );

        // create an imitation of a repository that will return a expectedQuestions list
        when( questionRepository.findAll() ).thenReturn( expectedQuestions );

        List<Question> actualQuestions = questionService.getAllQuestions();

        assertEquals( expectedQuestions.size(), actualQuestions.size() );
        for ( int i = 0; i < expectedQuestions.size(); i++ ) {
            assertEquals( expectedQuestions.get( i ), actualQuestions.get( i ) );
        }
    }
}
