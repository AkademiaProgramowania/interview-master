package pl.pop.interview.master.account;

import jakarta.persistence.*;
import lombok.*;
import pl.pop.interview.master.practitioner.Practitioner;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Practitioner practitioner;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
