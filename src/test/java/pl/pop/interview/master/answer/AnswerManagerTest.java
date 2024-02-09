package pl.pop.interview.master.answer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pop.interview.master.practitioner.Practitioner;
import pl.pop.interview.master.practitioner.PractitionerService;
import pl.pop.interview.master.practitioner.PractitionerServiceException;
import pl.pop.interview.master.question.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnswerManagerTest {
    private static final String YES = "Yes";
    private static final String NO = "No";
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private QuestionService questionService;
    @Mock
    private PractitionerService practitionerService;
    @InjectMocks
    private AnswerManager answerManager;

    @Test
    public void testFindRandomQuestion() {
        Question question = new Question();
        QuestionDTO questionDTO2 = new QuestionDTO();
        QuestionDTO questionDTO = new QuestionDTO();

        questionRepository.save(question);

        when(questionRepository.findRandomQuestion()).thenReturn(Optional.of(question));
        when(questionService.mapToDto(question)).thenReturn(questionDTO);
        assertSame(questionDTO, answerManager.findRandomQuestion());
        assertNotSame(questionDTO2, answerManager.findRandomQuestion());
    }

    @Test
    public void testSaveNewCorrectAnswer() {
        Practitioner mockPractitioner = mock(Practitioner.class);
        when(mockPractitioner.getId()).thenReturn( 1L );

        Question question = new Question("content", YesNo.YES);
        question.setId( 1L );

        Question anotherQuestion = new Question();
        anotherQuestion.setId( 2L );

        List<Answer> practitionerAnswers = new ArrayList<>(
                List.of( new Answer( 1L,
                        null,
                        null,
                        null,
                        mockPractitioner,
                        anotherQuestion ) )
        );

        AnswerDTO answerDTO = new AnswerDTO(
                1,
                "content",
                "YES",
                null,
                mockPractitioner.getId(),
                question.getId()
        );

        Answer expectedAnswer = new Answer(
                1L,
                "content",
                "YES",
                "Correct answer",
                mockPractitioner,
                question
                );

        when(questionRepository.findById( question.getId() )).thenReturn( Optional.of( question ) );
        when(practitionerService.getPractitioner( mockPractitioner.getId() )).thenReturn( mockPractitioner );
        when(mockPractitioner.getAnswers()).thenReturn( practitionerAnswers );
        when(answerRepository.save(any())).thenReturn( expectedAnswer );

       answerService.addNewAnswer( answerDTO );

        ArgumentCaptor<Answer> captor = ArgumentCaptor.forClass( Answer.class );
        verify(answerRepository).save( captor.capture() );

        AnswerDTO capturedAnswerDTO = AnswerDTO.mapToDto( captor.getValue() );

        assertEquals( AnswerDTO.mapToDto( expectedAnswer ).getQuestionContent(), capturedAnswerDTO.getQuestionContent() );
        assertEquals( AnswerDTO.mapToDto( expectedAnswer ).getAnswer(), capturedAnswerDTO.getAnswer() );
        assertEquals( AnswerDTO.mapToDto( expectedAnswer ).getResult(), capturedAnswerDTO.getResult() );
        assertEquals( AnswerDTO.mapToDto( expectedAnswer ).getPractitionerId(), capturedAnswerDTO.getPractitionerId() );
        assertEquals( AnswerDTO.mapToDto( expectedAnswer ).getQuestionId(), capturedAnswerDTO.getQuestionId() );
    }

    @Test
    public void testAddNewAnswer_QuestionIsAnswered() {
        Practitioner mockPractitioner = mock(Practitioner.class);
        when(mockPractitioner.getId()).thenReturn( 1L );

        Question mockQuestion = mock(Question.class);
        when(mockQuestion.getId()).thenReturn( 1L );

        List<Answer> practitionerAnswers = new ArrayList<>(
                List.of(new Answer(
                        1L,
                        null,
                        null,
                        null,
                        mockPractitioner,
                        mockQuestion ))
        );

        AnswerDTO answerDTO = new AnswerDTO(
                1,
                "content",
                "YES",
                null,
                mockPractitioner.getId(),
                mockQuestion.getId()
        );

        when(practitionerService.getPractitioner( mockPractitioner.getId() )).thenReturn( mockPractitioner );
        when(mockPractitioner.getAnswers()).thenReturn( practitionerAnswers );

        assertThrows( PractitionerServiceException.class,() -> answerService.addNewAnswer( answerDTO ) );
    }
}