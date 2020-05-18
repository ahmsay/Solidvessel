package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Account;
import com.shopping.accountservice.repositories.IAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccountService implements IAccountService {

    private IAccountRepository accountRepository;

    public AccountService(final IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Set<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    @Override
    public Account getAccountById(final int id) {
        return accountRepository.getAccountById(id);
    }
}
