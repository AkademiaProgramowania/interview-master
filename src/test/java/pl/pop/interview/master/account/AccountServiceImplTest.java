package pl.pop.interview.master.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AccountMapper accountMapper;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void testCreateNewAccount() {
        AccountDTO inputDTO = new AccountDTO("email@gmail.com", "password");
        Account account = new Account("email@gmail.com", "hashedPassword");

        when(accountRepository.findByEmail(inputDTO.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(inputDTO.getPassword())).thenReturn("hashedPassword");
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(accountMapper.accountToAccountDTO(account)).thenReturn(new AccountDTO("email@gmail.com", null));

        AccountDTO resultDTO = accountService.createNewAccount(inputDTO);

        assertNotNull(resultDTO);
        assertEquals("email@gmail.com", resultDTO.getEmail());
        assertNull(resultDTO.getPassword());
    }

    @Test
    void listAccounts() {
        Account account1 = new Account("email@gmail.com", "password");
        Account account2 = new Account("email2@gmail.com", "password2");
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        when(accountRepository.findAll()).thenReturn(accounts);
        when(accountMapper.accountToAccountDTO(any(Account.class)))
                .thenReturn(new AccountDTO("email@gmail.com", "hashed1"))
                .thenReturn(new AccountDTO("email2@gmail.com", "hashed2"));

        List<AccountDTO> resultList = accountService.listAccounts();
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals("email@gmail.com", resultList.get(0).getEmail());
        assertEquals("hashed1", resultList.get(0).getPassword());
    }
}