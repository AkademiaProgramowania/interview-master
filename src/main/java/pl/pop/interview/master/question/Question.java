package pl.pop.interview.master.question;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representing the question table inside DB with id, content and correct answer
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    @Column(name = "correct_answer")
    private String correctAnswer;

    // constructors

    public Question( String content, String correctAnswer ) {
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
