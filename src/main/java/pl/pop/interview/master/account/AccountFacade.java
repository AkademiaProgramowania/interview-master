package pl.pop.interview.master.account;

import java.util.List;

public interface AccountFacade {
    AccountDTO createNewAccount(AccountDTO accountDTO);
    List<AccountDTO> getAllAccounts();
}
