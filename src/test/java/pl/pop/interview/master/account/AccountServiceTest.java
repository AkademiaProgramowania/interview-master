package pl.pop.interview.master.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.pop.interview.master.practitioner.Practitioner;
import pl.pop.interview.master.practitioner.PractitionerService;
import pl.pop.interview.master.question.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {


    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PractitionerService practitionerService;
    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountService accountService;



    @Test
    void testCreateNewAccount() {
        AccountDTO inputDTO = new AccountDTO();
        inputDTO.setId(1L);
        inputDTO.setEmail("email@gmail.com");
        inputDTO.setPassword("hashedPassword");
        inputDTO.setPractitionerId(1L);

        Practitioner practitioner = new Practitioner(1L);
        Account result = new Account();
        result.setId(1L);
        result.setEmail("email@gmail.com");
        result.setPassword("hashedPassword");
        result.setPractitioner(practitioner);

        when(accountRepository.existsById(inputDTO.getId())).thenReturn(false);
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