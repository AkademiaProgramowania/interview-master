package pl.pop.interview.master.practitioner;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pop.interview.master.answer.Answer;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Practitioner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "practitioner", fetch = FetchType.LAZY)
    private List<Answer> answers;
}
