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
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountMapper = accountMapper;
    }


    public AccountDTO createNewAccount(AccountDTO accountDTO) {
        // ensure that there is no account with given email
        if (findById(accountDTO.getId())) {
            throw new AccountServiceException("An account with this email address already exists");
        }
        // create new account with a password hashed
        String hashedPassword = encodePassword(accountDTO.getPassword());
        Account account = new Account(accountDTO.getEmail(), hashedPassword);
        return accountMapper.mapEntityToDto(accountRepository.save(account));
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(account -> accountMapper.mapEntityToDto(account))
                .toList();
    }

    private boolean findById(Long id) {
        return accountRepository.existsById(id);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
