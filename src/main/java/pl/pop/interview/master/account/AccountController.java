package pl.pop.interview.master.account;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public void addNewAccount(@RequestBody AccountDTO accountDTO) {
        accountService.createNewAccount(accountDTO);
    }

    @GetMapping
    public List<AccountDTO> listAccounts() {
        return accountService.listAccounts();
    }


}
