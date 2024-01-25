package pl.pop.interview.master.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private long id;
    private String email;
    private String password;
    private long practitionerId;

    public AccountDTO(String email, String password, Long practitionerId) {
        this.email = email;
        this.password = password;
        this.practitionerId = practitionerId;
    }

    public AccountDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
