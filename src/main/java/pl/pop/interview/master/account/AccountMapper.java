package pl.pop.interview.master.account;

import eye2web.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pop.interview.master.practitioner.Practitioner;
import pl.pop.interview.master.practitioner.PractitionerService;

@AllArgsConstructor
@Component
public class AccountMapper {
    private ModelMapper modelMapper;


    public Account mapDtoToEntity(AccountDTO dto) {
        Account entity = modelMapper.map(dto, Account.class);
        Practitioner practitioner = new Practitioner();
        practitioner.setId(dto.getPractitionerId());
        entity.setPractitioner(practitioner);
        return entity;
    }

    public AccountDTO mapEntityToDto(Account entity) {
        AccountDTO dto = modelMapper.map(entity, AccountDTO.class);
        dto.setPractitionerId(entity.getPractitioner().getId());
        return dto;
    }
}
