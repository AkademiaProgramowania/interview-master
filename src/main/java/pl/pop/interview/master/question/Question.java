package pl.pop.interview.master.question;


import jakarta.persistence.*;
import lombok.*;

/**
 * Representing the question table inside DB with id, content and correct answer
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private boolean correctAnswer;

    public Question(String content, boolean correctAnswer) {
        this.content = content;
        this.correctAnswer = correctAnswer;
    }
}
