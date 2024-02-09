package pl.pop.interview.master.answer;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private int id;
    private String question;
    private boolean isCorrect;
    private String result;

    public static AnswerDTO mapToDto(Answer answer) {
        return AnswerDTO.builder()
                .question(answer.getQuestionContent())
                .isCorrect(answer.isCorrect())
                .result(answer.getResult())
                .build();
    }
}
