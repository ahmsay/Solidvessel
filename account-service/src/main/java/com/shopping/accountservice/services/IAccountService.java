package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Account;

import java.util.Set;

public interface IAccountService {

    Set<Account> getAllAccounts();

    Account getAccountById(int id);
}
