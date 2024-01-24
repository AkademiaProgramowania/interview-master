package pl.pop.interview.master.account;

import jakarta.persistence.*;
import lombok.*;
import pl.pop.interview.master.practitioner.Practitioner;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    private String email;
    private String password;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @OneToOne
    private Practitioner practitioner;
}
