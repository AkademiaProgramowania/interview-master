package pl.pop.interview.master.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    @InjectMocks
    private AccountService accountService;

    @Test
    void testCreateNewAccount() {
        AccountDTO inputDTO = new AccountDTO("email@gmail.com", "password");
        Account account = new Account("email@gmail.com", "hashedPassword");

        when(accountRepository.existsById(inputDTO.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(inputDTO.getPassword())).thenReturn("hashedPassword");
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountDTO resultDTO = accountService.createNewAccount(inputDTO);

        assertNotNull(resultDTO);
        assertEquals("email@gmail.com", resultDTO.getEmail());
        assertNull(resultDTO.getPassword()); //mapper sets hashed password to null to make it invisible
    }

    @Test
    void listAccounts() {
        List<Account> accounts = Arrays.asList(
                new Account("email@gmail.com", "password"),
                new Account("email2@gmail.com", "password2"));

        when(accountRepository.findAll()).thenReturn(accounts);

        List<AccountDTO> resultList = accountService.getAllAccounts();
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals("email@gmail.com", resultList.get(0).getEmail());
        assertNull(resultList.get(0).getPassword()); //mapper sets hashed password to null to make it invisible
    }
}
