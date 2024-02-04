package pl.pop.interview.master.answer;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private int id;
    private String question;
    private String answer;
    private String result;

    public static AnswerDTO mapToDto(Answer answer) {
        return AnswerDTO.builder()
                .question(answer.getQuestionContent())
                .answer(answer.getAnswer())
                .result(answer.getResult())
                .build();
    }
}
