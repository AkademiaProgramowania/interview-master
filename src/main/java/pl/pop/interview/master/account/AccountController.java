package pl.pop.interview.master.account;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/accounts")
class AccountController {

    private final AccountFacade accountService;

    @PostMapping
    void addNewAccount(@RequestBody AccountDTO accountDTO) {
        accountService.createNewAccount(accountDTO);
    }

    @GetMapping
    List<AccountDTO> listAccounts() {
        return accountService.getAllAccounts();
    }
}
