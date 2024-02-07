package pl.pop.interview.master.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO objects are used as data transport structure. 
 * Transport takes place between the http request/response body (as JSON) and the business logic layer (Services).
 * This class provides the JSON structure used in communication with the API.
 * 
 * Getters and no-args constructor are required by the Jackson library for JSON serialization/deserialization.
 */
@AllArgsConstructor
@Getter
@Setter
public class QuestionDTO {
    private Long id; // dodane pole by móc wyświetlić userowi pytanie z id a bez odpowiedzi
    private String content;
    private String correctAnswer;

    public QuestionDTO() {
    }
    public QuestionDTO(String content, String correctAnswer) {
        this.content = content;
        this.correctAnswer = correctAnswer;
    }
}
