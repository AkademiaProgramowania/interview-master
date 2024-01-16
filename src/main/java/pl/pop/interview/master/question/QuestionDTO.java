package pl.pop.interview.master.question;

/**
 * Data Transfer Object (DTO) representing questions, used for communication between layers.
 */

public class QuestionDTO {
    private String content;
    private YesNo correctAnswer;

    public QuestionDTO() {
    }

    public QuestionDTO( String content, YesNo correctAnswer ) {
        this.content = content;
        this.correctAnswer = correctAnswer;
    }

    // Getters are required by the Jackson library for JSON serialization/deserialization

    public String getContent() {
        return content;
    }

    public YesNo getCorrectAnswer() {
        return correctAnswer;
    }
}
