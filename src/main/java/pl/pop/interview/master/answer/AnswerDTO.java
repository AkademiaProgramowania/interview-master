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
    private boolean answer;
    private String result;

    public static AnswerDTO mapToDto(Answer answer) {
        return AnswerDTO.builder()
                .question(answer.getQuestionContent())
                .answer(answer.isAnswer())
                .result(answer.getResult())
                .build();
    }
}
