package com.melvstein.banking.app.service;

import com.melvstein.banking.app.dto.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    List<AccountDto> getAccounts();
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    void deleteAccountById(Long id);
}
