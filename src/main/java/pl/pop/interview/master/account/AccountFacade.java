package pl.pop.interview.master.account;

import java.util.List;
/**
 * The {@code AccountFacade} interface defines high-level operations for managing user accounts.
 * Implementing classes are responsible for providing the actual logic to create new accounts
 * and retrieve information about existing accounts.
 */
public interface AccountFacade {
    AccountDTO createNewAccount(AccountDTO accountDTO);

    List<AccountDTO> getAllAccounts();
}
