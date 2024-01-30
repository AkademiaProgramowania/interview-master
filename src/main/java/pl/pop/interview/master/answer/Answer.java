package pl.pop.interview.master.answer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionContent;
    private String answer;
    private String result;

    public String getQuestionContent() {
        return questionContent;
    }

    public String getAnswer() {
        return answer;
    }

    public String getResult() {
        return result;
    }
}
