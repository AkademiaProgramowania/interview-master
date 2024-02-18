package pl.pop.interview.master.answer;

import jakarta.persistence.*;
import lombok.*;
import pl.pop.interview.master.practitioner.Practitioner;
import pl.pop.interview.master.question.Question;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionContent;
    private boolean givenAnswer;
    private String result;
    @ManyToOne
    @JoinColumn(name = "practitioner_id")
    private Practitioner practitioner;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer( String questionContent, boolean givenAnswer, String result ) {
        this.questionContent = questionContent;
        this.givenAnswer = givenAnswer;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public boolean getGivenAnswer() {
        return givenAnswer;
    }

    public String getResult() {
        return result;
    }

    public Practitioner getPractitioner() {
        return practitioner;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestionContent( String questionContent ) {
        this.questionContent = questionContent;
    }

    public void setGivenAnswer( boolean answer ) {
        this.givenAnswer = answer;
    }

    public void setResult( String result ) {
        this.result = result;
    }

    public void setPractitioner( Practitioner practitioner ) {
        this.practitioner = practitioner;
    }

    public void setQuestion( Question question ) {
        this.question = question;
    }
}
