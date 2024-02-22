package pl.pop.interview.master.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM Question ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Question> findRandomQuestion();

    @Query(value = "SELECT q.* FROM Question q JOIN Answer a ON q.id = a.question_id WHERE a.practitioner_id = :practitionerId",
            countQuery = "SELECT COUNT(q.id) FROM Question q JOIN Answer a ON q.id = a.question_id WHERE a.practitioner_id = :practitionerId",
            nativeQuery = true)
    Page<Question> getQuestionsAnsweredByPractitioner(@Param("practitionerId") Long practitionerId, Pageable pageable);

    @Query(value = "SELECT q.* FROM Question q LEFT JOIN Answer a ON q.id = a.question_id AND a.practitioner_id = :practitionerId WHERE a.id IS NULL",
            countQuery = "SELECT COUNT(q.id) FROM Question q LEFT JOIN Answer a ON q.id = a.question_id AND a.practitioner_id = :practitionerId WHERE a.id IS NULL",
            nativeQuery = true)
    Page<Question>getQuestionsUnansweredByPractitioner(@Param("practitionerId") Long practitionerId, Pageable pageable);
}
