package pl.pop.interview.master.question;

import lombok.ToString;

/**
 * DTO objects are used as data transport structure. 
 * Transport takes place between the http request/response body (as JSON) and the business logic layer (Services).
 * This class provides the JSON structure used in communication with the API.
 * 
 * Getters and no-args constructor are required by the Jackson library for JSON serialization/deserialization.
 */

public class QuestionDTO {
    private String content;
    private String correctAnswer;

    public QuestionDTO() {
    }

    public QuestionDTO( String content, String correctAnswer ) {
        this.content = content;
        this.correctAnswer = correctAnswer;
    }


    public String getContent() {
        return content;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
