package pl.pop.interview.master.account;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountMapper = accountMapper;
    }

    public AccountDTO createNewAccount(AccountDTO accountDTO) {
        // ensure that there is no account with given email
        if (accountRepository.findByEmail(accountDTO.getEmail()) != null) {
            throw new AccountServiceException("An account with this email address already exists");
        } else {
            // create new account with a password hashed
            String hashedPassword = passwordEncoder.encode(accountDTO.getPassword());
            Account account = new Account(accountDTO.getEmail(), hashedPassword);
            return accountMapper.accountToAccountDTO(accountRepository.save(account));
        }
    }

    @Override
    public List<AccountDTO> listAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(account -> accountMapper.accountToAccountDTO(account))
                .collect(Collectors.toList());
    }
}
