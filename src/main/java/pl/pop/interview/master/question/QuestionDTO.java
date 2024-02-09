package pl.pop.interview.master.question;

import lombok.AllArgsConstructor;
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
    private boolean yesNo;

    public QuestionDTO() {
    }
    public QuestionDTO(String content, boolean yesNo ) {
        this.content = content;
        this.yesNo = yesNo;
    }
}
