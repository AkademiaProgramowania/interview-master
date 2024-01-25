package pl.pop.interview.master.account;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/add")
    public void addNewAccount(@RequestBody AccountDTO accountDTO) {
        accountService.createNewAccount(accountDTO);
    }

    @GetMapping("/list")
    public List<AccountDTO> listAccounts() {
        return accountService.getAllAccounts();
    }


}
