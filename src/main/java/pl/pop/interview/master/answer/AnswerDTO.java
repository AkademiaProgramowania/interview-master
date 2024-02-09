package pl.pop.interview.master.answer;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private int id;
    private String questionContent;
    private String answer;
    private String result;
    private Long practitionerId;
    private Long questionId;

    public static AnswerDTO mapToDto(Answer answer) {
        return AnswerDTO.builder()
                .questionContent(answer.getQuestionContent())
                .answer(answer.getAnswer())
                .result(answer.getResult())
                .practitionerId( answer.getPractitioner().getId() )
                .questionId( answer.getQuestion().getId() )
                .build();
    }
}
