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

    public static AccountDTO mapToDto(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(account.getEmail());
        accountDTO.setPassword(null); // good practice: password fields not visible in result DTO
        if (account.getPractitioner() != null) {
            accountDTO.setPractitionerId(account.getPractitioner().getId());
        }
        return accountDTO;
    }

}
