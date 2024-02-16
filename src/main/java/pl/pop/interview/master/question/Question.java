package pl.pop.interview.master.question;


import jakarta.persistence.*;
import lombok.*;
import pl.pop.interview.master.answer.Answer;

import java.util.List;

/**
 * Representing the question table inside DB with id, content and correct answer
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private boolean expectedAnswer;
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answer> answers;

    public Question(String content, boolean expectedAnswer ) {
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setContent( String content ) {
        this.content = content;
    }

    public void setExpectedAnswer( boolean correctAnswer ) {
        this.expectedAnswer = correctAnswer;
    }
}
