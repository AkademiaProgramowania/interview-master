package pl.pop.interview.master.account;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/accounts")
class AccountController {

    private final AccountFacade accountFacade;

    @PostMapping
    void addNewAccount(@RequestBody AccountDTO accountDTO) {
        accountFacade.createNewAccount(accountDTO);
    }

    @GetMapping
    List<AccountDTO> listAccounts() {
        return accountFacade.getAllAccounts();
    }
}
