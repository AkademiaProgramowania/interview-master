package pl.pop.interview.master.question;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
}
