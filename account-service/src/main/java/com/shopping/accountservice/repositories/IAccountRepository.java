package com.shopping.accountservice.repositories;

import com.shopping.accountservice.entity.Account;

import java.util.Set;

public interface IAccountRepository {

    Set<Account> getAllAccounts();

    Account getAccountById(int id);
}
