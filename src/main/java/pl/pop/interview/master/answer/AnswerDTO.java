package pl.pop.interview.master.answer;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class AnswerDTO {
    private int id;
    private String question;
    private String answer;
    private String result;

    public AnswerDTO(String question, String answer, String result) {
        this.question = question;
        this.answer = answer;
        this.result = result;
    }
}
