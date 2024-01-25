package pl.pop.interview.master.practitioner;

import eye2web.modelmapper.ModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PractitionerMapperTest {

    private static PractitionerMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = new PractitionerMapper(new ModelMapper());
    }

    @Test
    void mapDtoToEntity() {
        long practitionerId = 1L;
        // Given
        PractitionerDTO dto = new PractitionerDTO();
        dto.setId(practitionerId);

        // When
        Practitioner entity = underTest.mapDtoToEntity(dto);

        // Then
        assertEquals(dto.getId(), entity.getId());
    }

    @Test
    void mapEntityToDto() {
        // Given
        Long practitionerId = 1L;
        Practitioner entity = new Practitioner();
        entity.setId(practitionerId);

        // When
        PractitionerDTO dto = underTest.mapEntityToDto(entity);

        // Then
        assertEquals(entity.getId(), dto.getId());
    }
}