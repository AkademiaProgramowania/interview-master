package pl.pop.interview.master.question;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO objects are used as data transport structure. 
 * Transport takes place between the http request/response body (as JSON) and the business logic layer (Services).
 * This class provides the JSON structure used in communication with the API.
 * 
 * Getters and no-args constructor are required by the Jackson library for JSON serialization/deserialization.
 */
@AllArgsConstructor
@Data
public class QuestionDTO {
    private String content;
    private YesNo correctAnswer;

}
