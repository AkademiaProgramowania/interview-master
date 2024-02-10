package pl.pop.interview.master.practitioner;

import jakarta.persistence.*;
import lombok.*;
import pl.pop.interview.master.answer.Answer;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Practitioner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "practitioner", fetch = FetchType.LAZY)
    private List<Answer> answers;
}
