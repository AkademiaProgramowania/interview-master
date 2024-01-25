package pl.pop.interview.master.practitioner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Entity(name = "practitioners")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Practitioner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
