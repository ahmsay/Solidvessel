package com.shopping.accountservice.controllers;

import com.shopping.accountservice.entity.Account;
import com.shopping.accountservice.services.IAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    private IAccountService accountService;

    public AccountRestController(final IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public Set<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable("accountId") final int id) {
        return accountService.getAccountById(id);
    }
}
