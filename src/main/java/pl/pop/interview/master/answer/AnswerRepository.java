package pl.pop.interview.master.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query( """
            SELECT CASE 
            WHEN COUNT(answer) > 0
            THEN true ELSE false
            END
            FROM Answer answer
            WHERE answer.practitioner.id = :practitionerId
            AND answer.question.id = :questionId
            AND answer.result = 'Correct'
            """ )
    boolean isQuestionAnswered( @Param( "practitionerId" ) Long practitionerId,
                                @Param( "questionId" ) Long questionId );
}
