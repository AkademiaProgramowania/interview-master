package pl.pop.interview.master.account;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pop.interview.master.practitioner.Practitioner;
import pl.pop.interview.master.practitioner.PractitionerService;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final PractitionerService practitionerService;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, PractitionerService practitionerService) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.practitionerService = practitionerService;
    }

    public AccountDTO createNewAccount(AccountDTO accountDTO) {
        // ensure that there is no account with given email
        if (accountRepository.existsById(accountDTO.getEmail())) {
            throw new AccountServiceException("An account with this email address already exists");
        }
        // create new account with a password hashed
        String hashedPassword = passwordEncoder.encode(accountDTO.getPassword());
        Account account = new Account(accountDTO.getEmail(), hashedPassword);
        // create a new practitioner and set to the account
        Practitioner practitioner = practitionerService.createNewPractitioner();
        account.setPractitioner(practitioner);
        return mapToDto(accountRepository.save(account));
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(account -> mapToDto(account))
                .toList();
    }

    private AccountDTO mapToDto(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(account.getEmail());
        accountDTO.setPassword(null); // good practice: password fields not visible in result DTO
        if (account.getPractitioner() != null) {
            accountDTO.setPractitionerId(account.getPractitioner().getId());
        }
        return accountDTO;
    }

    private Account mapToEntity(AccountDTO accountDTO) {
        return new Account(accountDTO.getEmail(), accountDTO.getPassword());
    }
}
