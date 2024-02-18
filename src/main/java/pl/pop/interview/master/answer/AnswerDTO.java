package pl.pop.interview.master.answer;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private int id;
    private String questionContent;
    private boolean givenAnswer;
    private String result;
    private Long practitionerId;
    private Long questionId;

    public static AnswerDTO mapToDto(Answer answer) {
        return AnswerDTO.builder()
                .questionContent(answer.getQuestionContent())
                .givenAnswer(answer.getGivenAnswer())
                .result(answer.getResult())
                .practitionerId( answer.getPractitioner().getId() )
                .questionId( answer.getQuestion().getId() )
                .build();
    }

    public int getId() {
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

    public Long getPractitionerId() {
        return practitionerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionContent( String questionContent ) {
        this.questionContent = questionContent;
    }

    public void setGivenAnswer( boolean givenAnswer ) {
        this.givenAnswer = givenAnswer;
    }

    public void setResult( String result ) {
        this.result = result;
    }
}
