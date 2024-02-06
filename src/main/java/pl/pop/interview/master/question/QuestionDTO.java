package pl.pop.interview.master.question;

/**
 * DTO objects are used as data transport structure. 
 * Transport takes place between the http request/response body (as JSON) and the business logic layer (Services).
 * This class provides the JSON structure used in communication with the API.
 * 
 * Getters and no-args constructor are required by the Jackson library for JSON serialization/deserialization.
 */

public class QuestionDTO {
    private Long id; // dodane pole by móc wyświetlić userowi pytanie z id a bez odpowiedzi
    private String content;
    private String correctAnswer;

    public QuestionDTO() {
    }

    public QuestionDTO(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public QuestionDTO(Long id, String content, String correctAnswer) {
        this.id = id;
        this.content = content;
        this.correctAnswer = correctAnswer;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }


    public String getCorrectAnswer() {
        return correctAnswer;
}

    public void setContent(String content) {
        this.content = content;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
