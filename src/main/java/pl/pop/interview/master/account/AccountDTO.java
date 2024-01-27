package pl.pop.interview.master.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String email;
    private String password;
    private Long practitionerId;

    public AccountDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
