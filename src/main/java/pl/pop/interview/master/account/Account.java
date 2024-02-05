package pl.pop.interview.master.account;

import jakarta.persistence.*;
import lombok.*;
import pl.pop.interview.master.practitioner.Practitioner;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String email;
    private String password;

    @OneToOne
    private Practitioner practitioner;
}
