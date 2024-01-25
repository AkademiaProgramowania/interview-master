package pl.pop.interview.master.question;

import eye2web.modelmapper.ModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionMapperTest {
    private static QuestionMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = new QuestionMapper(new ModelMapper());
    }

    @Test
    void mapDtoToEntity() {
        long questionId = 1L;
        String content = "What is your favorite programming language?";
        String correctAnswer = "Java";

        // Given
        QuestionDTO dto = new QuestionDTO();
        dto.setId(questionId);
        dto.setContent(content);
        dto.setCorrectAnswer(correctAnswer);

        // When
        Question entity = underTest.mapDtoToEntity(dto);

        // Then
        assertAll(
                () -> assertEquals(dto.getId(), entity.getId()),
                () -> assertEquals(dto.getContent(), entity.getContent()),
                () -> assertEquals(dto.getCorrectAnswer(), entity.getCorrectAnswer())
        );
    }

    @Test
    void mapEntityToDto() {
        // Given
        Long questionId = 1L;
        String content = "What is your favorite programming language?";
        String correctAnswer = "Java";

        Question entity = new Question();
        entity.setId(questionId);
        entity.setContent(content);
        entity.setCorrectAnswer(correctAnswer);

        // When
        QuestionDTO dto = underTest.mapEntityToDto(entity);

        // Then
        assertAll(
                () -> assertEquals(entity.getId(), dto.getId()),
                () -> assertEquals(entity.getContent(), dto.getContent()),
                () -> assertEquals(entity.getCorrectAnswer(), dto.getCorrectAnswer())
        );
    }
}