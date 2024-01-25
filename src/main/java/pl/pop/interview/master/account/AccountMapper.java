package pl.pop.interview.master.account;

import eye2web.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AccountMapper {
    private ModelMapper modelMapper;

    public Account mapDtoToEntity(AccountDTO dto) {
        return modelMapper.map(dto, Account.class);
    }

    public AccountDTO mapEntityToDto(Account entity) {
        return modelMapper.map(entity, AccountDTO.class);
    }
}
