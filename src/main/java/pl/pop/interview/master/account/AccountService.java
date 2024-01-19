package pl.pop.interview.master.account;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AccountDTO createNewAccount(AccountDTO accountDTO) {
        // ensure that there is no account with given email
        if (accountRepository.existsById(accountDTO.getEmail())) {
            throw new AccountServiceException("An account with this email address already exists");
        }
        // create new account with a password hashed
        String hashedPassword = passwordEncoder.encode(accountDTO.getPassword());
        Account account = new Account(accountDTO.getEmail(), hashedPassword);
        return buildAccountDTOFromAccount(accountRepository.save(account));
    }

    public List<AccountDTO> listAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(account -> buildAccountDTOFromAccount(account))
                .toList();
    }

    private AccountDTO buildAccountDTOFromAccount(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(account.getEmail());
        accountDTO.setPassword(null); // good practice: password fields not visible in result DTO // todo spr czy jeśli null to nie dawać do jsona zbędnego pola
        return accountDTO;
    }

    private Account buildAccountFromAccountDTO(AccountDTO accountDTO) {
        return new Account(accountDTO.getEmail(), accountDTO.getPassword());
    }
}
