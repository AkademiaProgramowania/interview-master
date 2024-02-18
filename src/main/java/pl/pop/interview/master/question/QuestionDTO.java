package pl.pop.interview.master.question;

import lombok.AllArgsConstructor;

/**
 * DTO objects are used as data transport structure. 
 * Transport takes place between the http request/response body (as JSON) and the business logic layer (Services).
 * This class provides the JSON structure used in communication with the API.
 * 
 * Getters and no-args constructor are required by the Jackson library for JSON serialization/deserialization.
 */
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private String content;
    private boolean expectedAnswer;

    public QuestionDTO() {
    }
    public QuestionDTO(String content, boolean expectedAnswer ) {
        this.content = content;
        this.expectedAnswer = expectedAnswer;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean getExpectedAnswer() {
        return expectedAnswer;
    }

    public void setContent( String content ) {
        this.content = content;
    }

    public void setExpectedAnswer( boolean expectedAnswer ) {
        this.expectedAnswer = expectedAnswer;
    }
}
