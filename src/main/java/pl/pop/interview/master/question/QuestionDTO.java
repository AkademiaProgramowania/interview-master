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
    private YesNo correctAnswer;


    public QuestionDTO() {
    }

    public QuestionDTO(Long id, String content) { // konstruktor dla usera do udzielania odpowiedzi
        this.id = id;
        this.content = content;
    }

    public QuestionDTO(Long id, String content, YesNo correctAnswer) { // dla admina do updatowania listy pytań
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
    public YesNo getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionDTO that)) return false;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getContent() != null ? !getContent().equals(that.getContent()) : that.getContent() != null) return false;
        return getCorrectAnswer() == that.getCorrectAnswer();
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (getCorrectAnswer() != null ? getCorrectAnswer().hashCode() : 0);
        return result;
    }
}
