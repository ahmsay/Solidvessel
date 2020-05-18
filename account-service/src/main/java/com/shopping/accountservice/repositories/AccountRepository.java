package com.shopping.accountservice.repositories;

import com.shopping.accountservice.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class AccountRepository implements IAccountRepository {

    private Set<Account> accounts;

    public AccountRepository() {
        accounts = new HashSet<>();
        accounts.add(new Account(1, "Zorkov"));
        accounts.add(new Account(2, "Lorne"));
        accounts.add(new Account(3, "Matthias"));
    }

    @Override
    public Set<Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public Account getAccountById(final int id) {
        return accounts.stream()
                .filter(account -> account.getId() == id)
                .findAny()
                .orElse(null);
    }
}
