package pl.pop.interview.master.account;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AccountDTO createNewAccount(AccountDTO accountDTO) {
        // ensure that there is no account with given email
        if (accountRepository.findByEmail(accountDTO.getEmail()) != null) {
            throw new AccountServiceException("An account with this email address already exists");
        } else {
            // create new account with a password hashed
            String hashedPassword = passwordEncoder.encode(accountDTO.getPassword());
            Account account = new Account(accountDTO.getEmail(), hashedPassword);
            return accountToAccountDTO(accountRepository.save(account));
        }
    }

    @Override
    public List<AccountDTO> listAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(account -> accountToAccountDTO(account))
                .collect(Collectors.toList());
    }

    public AccountDTO accountToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(account.getEmail());
        accountDTO.setPassword(null); // good practice: password fields not visible in result DTO
        return accountDTO;
    }

    public Account accountDTOToAccount(AccountDTO accountDTO) {
        return new Account(accountDTO.getEmail(), accountDTO.getPassword());
    }
}
