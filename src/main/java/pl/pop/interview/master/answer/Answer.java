package pl.pop.interview.master.answer;

import jakarta.persistence.*;
import lombok.*;
import pl.pop.interview.master.practitioner.Practitioner;
import pl.pop.interview.master.question.Question;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionContent;
    private String answer;
    private String result;
    @ManyToOne
    @JoinColumn(name = "practitioner_id")
    private Practitioner practitioner;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    // constructor without Practitioner and Question fields
    public Answer( String questionContent, String answer, String result ) {
        this.questionContent = questionContent;
        this.answer = answer;
        this.result = result;
    }
}
