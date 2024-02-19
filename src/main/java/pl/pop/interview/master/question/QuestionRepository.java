package pl.pop.interview.master.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM Question ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Question> findRandomQuestion();

    @Query("SELECT q FROM Question q JOIN q.answers a WHERE a.practitioner.id = :practitionerId")
    List<Question> findQuestionsAnsweredByPractitioner(@Param("practitionerId") Long practitionerId);
}
