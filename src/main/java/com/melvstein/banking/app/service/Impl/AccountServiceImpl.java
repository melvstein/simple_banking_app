package com.melvstein.banking.app.service.Impl;

import com.melvstein.banking.app.entity.Account;
import com.melvstein.banking.app.mapper.AccountMapper;
import com.melvstein.banking.app.repository.AccountRepository;
import com.melvstein.banking.app.dto.AccountDto;
import com.melvstein.banking.app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.toEntity(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toDto(savedAccount);
    }
}
