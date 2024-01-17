package pl.pop.interview.master.account;

import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {

    //Account accountDTOToAccount (AccountDTO accountDTO);

    AccountDTO accountToAccountDTO (Account account);
}
