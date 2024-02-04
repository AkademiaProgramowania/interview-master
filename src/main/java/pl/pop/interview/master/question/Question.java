package pl.pop.interview.master.question;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Representing the question table inside DB with id, content and correct answer
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Question {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    @Enumerated(EnumType.STRING)
    private YesNo correctAnswer;

    public Question(String content, YesNo correctAnswer) {
        this.content = content;
        this.correctAnswer = correctAnswer;
    }
}
