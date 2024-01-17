package pl.pop.interview.master.account;

import java.util.List;

public interface AccountService {

    AccountDTO createNewAccount(AccountDTO accountDTO);

    List<AccountDTO> listAccounts();
}
