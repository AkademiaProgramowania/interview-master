package pl.pop.interview.master.answer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pop.interview.master.question.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private AnswerService answerService;

    @Test
    public void testFindRandomQuestion() {
        Question question = new Question();
        QuestionDTO questionDTO2 = new QuestionDTO();
        QuestionDTO questionDTO = new QuestionDTO();
        questionRepository.save(question);
        when(questionRepository.findRandomQuestion()).thenReturn(Optional.of(question));
        when(questionService.mapToDto(question)).thenReturn(questionDTO);
        assertSame(questionDTO, answerService.findRandomQuestion());
        assertNotSame(questionDTO2, answerService.findRandomQuestion());
    }

    @Test
    public void testSaveNewCorrectAnswer() {
        Question question1 = new Question("Question1Content", YesNo.YES);
        Answer answerCorrect = new Answer();
        answerCorrect.setQuestionContent("Question1Content");
        answerCorrect.setAnswer("YES");
        answerCorrect.setResult("Correct answer");
        AnswerDTO answerDTOCorrect = new AnswerDTO();
        answerDTOCorrect.setResult("Correct answer");

        when(questionRepository.findById(1L)).thenReturn(Optional.of(question1));
        when(answerRepository.save(any())).thenReturn(answerCorrect);

        AnswerDTO resultCorrect = answerService.save(1L, "YES");

        ArgumentCaptor<Answer> answerCaptor = ArgumentCaptor.forClass(Answer.class);
        verify(answerRepository).save(answerCaptor.capture());
        Answer savedAnswer = answerCaptor.getValue();

        assertEquals(question1.getContent(), savedAnswer.getQuestionContent());
        assertEquals(question1.getCorrectAnswer().toString(), savedAnswer.getAnswer());
        assertEquals(question1.getContent(), resultCorrect.getQuestion());
        assertEquals(question1.getCorrectAnswer().toString(), resultCorrect.getAnswer());
    }

    @Test
    public void testSaveIncorrectAnswer() {
        Question question1 = new Question("QuestionContent", YesNo.YES);
        Answer answerIncorrect = new Answer();
        answerIncorrect.setQuestionContent("QuestionContent");
        answerIncorrect.setAnswer("NO");
        answerIncorrect.setResult("Incorrect answer or answer format YES/NO");
        AnswerDTO answerDTOIncorrect = new AnswerDTO();
        answerDTOIncorrect.setResult("Incorrect answer or answer format YES/NO");

        when(questionRepository.findById(1L)).thenReturn(Optional.of(question1));
        when(answerRepository.save(any())).thenReturn(answerIncorrect);

        AnswerDTO resultIncorrect = answerService.save(1L, "NO");

        ArgumentCaptor<Answer> answerCaptor = ArgumentCaptor.forClass(Answer.class);
        verify(answerRepository).save(answerCaptor.capture());
        Answer savedAnswer = answerCaptor.getValue();

        assertEquals(question1.getContent(), savedAnswer.getQuestionContent());
        assertNotEquals(question1.getCorrectAnswer().toString(), savedAnswer.getAnswer());
        assertEquals(question1.getContent(), resultIncorrect.getQuestion());
        assertNotEquals(question1.getCorrectAnswer().toString(), resultIncorrect.getAnswer());
    }
}