package pl.pop.interview.master.question;
import jakarta.persistence.*;
import lombok.*;
import pl.pop.interview.master.answer.Answer;

import java.util.List;
import java.util.Objects;

/**
 * Representing the question table inside DB with id, content and correct answer
 */

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private boolean correctAnswer;
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answer> answer;

    public Question(String content, boolean correctAnswer) {
        this.content = content;
        this.correctAnswer = correctAnswer;
    }
}
