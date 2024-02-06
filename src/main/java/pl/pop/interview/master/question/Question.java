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
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    @Enumerated( EnumType.STRING )
    private YesNo correctAnswer;
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answer> answer;

    public Question( String content, YesNo correctAnswer ) {
        this.content = content;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null || getClass() != obj.getClass() ) return false;

        Question question = ( Question ) obj;

        return Objects.equals( content, question.content ) && correctAnswer == question.getCorrectAnswer();
    }

    @Override
    public int hashCode() {
        return Objects.hash( content, correctAnswer );
    }
}
