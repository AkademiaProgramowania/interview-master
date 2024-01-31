package pl.pop.interview.master.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.pop.interview.master.practitioner.Practitioner;
import pl.pop.interview.master.practitioner.PractitionerService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private PractitionerService practitionerService;
    @InjectMocks
    private AccountService accountService;

    @Test
    void testCreateNewAccount() {
        AccountDTO inputDTO = new AccountDTO();
        inputDTO.setEmail("email@gmail.com");
        inputDTO.setPassword("password");
        Practitioner practitioner = new Practitioner();
        practitioner.setId(1L);
        Account result = new Account("email@gmail.com", "hashedPassword", practitioner);

        when(accountRepository.existsById(inputDTO.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(inputDTO.getPassword())).thenReturn("hashedPassword");
        when(practitionerService.createNewPractitioner()).thenReturn(practitioner);
        when(accountRepository.save(any(Account.class))).thenReturn(result);

        AccountDTO resultDTO = accountService.createNewAccount(inputDTO);
        assertNull(resultDTO.getPassword()); //mapper sets hashed password to null to make it invisible

        // capture account object to test accountRepository.save method result
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).save(accountCaptor.capture());
        Account savedAccount = accountCaptor.getValue();

        // check if captured object meets required conditions
        assertEquals("email@gmail.com", savedAccount.getEmail());
        assertEquals("hashedPassword", savedAccount.getPassword());
        assertNotNull(savedAccount.getPractitioner());
        assertEquals(1L, savedAccount.getPractitioner().getId());
    }

    @Test
    void listAccounts() {
        Account account1 = new Account();
        account1.setEmail("email@gmail.com");
        account1.setPassword("password");
        Account account2 = new Account();
        account2.setEmail("email2@gmail.com");
        account2.setEmail("password2");
        List<Account> accounts = Arrays.asList(account1, account2);

        when(accountRepository.findAll()).thenReturn(accounts);

        List<AccountDTO> resultList = accountService.getAllAccounts();
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals("email@gmail.com", resultList.get(0).getEmail());
        assertNull(resultList.get(0).getPassword()); //mapper sets hashed password to null to make it invisible
    }
}