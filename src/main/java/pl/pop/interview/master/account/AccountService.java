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
        if (accountRepository.existsById(accountDTO.getEmail())) {
            throw new AccountServiceException("An account with this email address already exists");
        }
        String hashedPassword = passwordEncoder.encode(accountDTO.getPassword());
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(hashedPassword);

        Practitioner practitioner = practitionerService.createNewPractitioner();
        account.setPractitioner(practitioner);
        return AccountDTO.mapToDto(accountRepository.save(account));
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDTO::mapToDto)
                .toList();
    }
}
