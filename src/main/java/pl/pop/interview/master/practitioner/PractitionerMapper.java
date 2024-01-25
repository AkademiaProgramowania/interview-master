package pl.pop.interview.master.practitioner;

import eye2web.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PractitionerMapper {
    private ModelMapper modelMapper;

    public PractitionerDTO mapEntityToDto(Practitioner entity) {
        return modelMapper.map(entity, PractitionerDTO.class);
    }

    public Practitioner mapDtoToEntity(PractitionerDTO dto) {
        return modelMapper.map(dto, Practitioner.class);
    }
}