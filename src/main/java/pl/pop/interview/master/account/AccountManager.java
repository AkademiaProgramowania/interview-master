package pl.pop.interview.master.account;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pop.interview.master.practitioner.Practitioner;
import pl.pop.interview.master.practitioner.PractitionerService;

import java.util.List;

@Service
class AccountManager implements AccountFacade {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final PractitionerService practitionerService;

    public AccountManager(AccountRepository accountRepository, PasswordEncoder passwordEncoder, PractitionerService practitionerService) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.practitionerService = practitionerService;
    }

    @Override
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

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(account -> mapToDto(account))
                .toList();
    }

    private AccountDTO mapToDto(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(account.getEmail());
        accountDTO.setPassword(null); // good practice: password fields not visible in result DTO // todo spr czy jeśli null to nie dawać do jsona zbędnego pola
        if (account.getPractitioner() != null) {
            accountDTO.setPractitionerId(account.getPractitioner().getId());
        }
        return accountDTO;
    }

    private Account mapToEntity(AccountDTO accountDTO) {
        return new Account(accountDTO.getEmail(), accountDTO.getPassword());
    }
}
