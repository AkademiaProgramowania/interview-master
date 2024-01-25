package pl.pop.interview.master.account;

import eye2web.modelmapper.ModelMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pop.interview.master.practitioner.Practitioner;
import pl.pop.interview.master.practitioner.PractitionerMapper;
import pl.pop.interview.master.practitioner.PractitionerService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
class AccountMapperTest {
    private static AccountMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = new AccountMapper(new ModelMapper());
    }

    @Test
    void mapDtoToEntity() {
        long accountId = 1L;
        long practitionerId = 1L;
        String email = "test@email.com";
        String password = "testPassword";
        // Given
        AccountDTO dto = new AccountDTO();
        dto.setId(accountId);
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setPractitionerId(practitionerId);

        // When
        Account entity = underTest.mapDtoToEntity(dto);

        // Then
        assertAll(
                () -> assertEquals(dto.getId(), entity.getId()),
                () -> assertEquals(dto.getEmail(), entity.getEmail()),
                () -> assertEquals(dto.getPassword(), entity.getPassword()),
                () -> assertEquals(dto.getPractitionerId(), entity.getPractitioner().getId())
        );
    }

    @Test
    void mapEntityToDto() {
        // Given
        Long accountId = 1L;
        Long practitionerId = 1L;
        String email = "test@email.com";
        String password = "testPassword";
        Account entity = new Account();
        entity.setId(accountId);
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setPractitioner(new Practitioner(practitionerId));

        // When
        AccountDTO dto = underTest.mapEntityToDto(entity);

        // Then
        assertAll(
                () -> assertEquals(entity.getId(), dto.getId()),
                () -> assertEquals(entity.getEmail(), dto.getEmail()),
                () -> assertEquals(entity.getPassword(), dto.getPassword()),
                () -> assertEquals(entity.getPractitioner().getId(), dto.getPractitionerId())
        );
    }
}