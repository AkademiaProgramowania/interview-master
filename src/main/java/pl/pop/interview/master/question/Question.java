package pl.pop.interview.master.question;


import jakarta.persistence.*;

import java.util.Objects;

/**
 * Representing the question table inside DB with id, content and correct answer
 */

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    @Enumerated( EnumType.STRING )
    private YesNo correctAnswer;

    // constructors

    public Question() {
    }

    public Question( String content, YesNo correctAnswer ) {
        this.content = content;
        this.correctAnswer = correctAnswer;
    }

    // getters

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public YesNo getCorrectAnswer() {
        return correctAnswer;
    }

    // setters

    public void setContent( String content ) {
        this.content = content;
    }

    public void setCorrectAnswer( YesNo correctAnswer ) {
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
