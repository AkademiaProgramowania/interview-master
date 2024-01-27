package pl.pop.interview.master.account;

import jakarta.persistence.*;
import lombok.*;
import pl.pop.interview.master.practitioner.Practitioner;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Account {

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
