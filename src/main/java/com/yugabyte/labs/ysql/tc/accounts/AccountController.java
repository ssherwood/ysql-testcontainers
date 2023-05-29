package com.yugabyte.labs.ysql.tc.accounts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping(AccountController.ACCOUNTS_PATH)
public class AccountController {
    public static final String ACCOUNTS_PATH = "/api/accounts";

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable UUID id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account, UriComponentsBuilder uriBuilder) {
        var newAccount = accountService.createAccount(account);
        var location = uriBuilder.path(ACCOUNTS_PATH + "/{id}").buildAndExpand(newAccount.getId()).toUri();
        return ResponseEntity.created(location).body(account);
    }
}