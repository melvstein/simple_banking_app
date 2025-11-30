package com.melvstein.banking.app.service.Impl;

import com.melvstein.banking.app.entity.Account;
import com.melvstein.banking.app.mapper.AccountMapper;
import com.melvstein.banking.app.repository.AccountRepository;
import com.melvstein.banking.app.dto.AccountDto;
import com.melvstein.banking.app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.toEntity(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts
                .stream()
                .map(AccountMapper::toDto)
                .toList();
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElse(null);

        return AccountMapper.toDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not exists"));

        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.toDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));

        if (amount > account.getBalance()) {
            throw new RuntimeException("Withdraw failed. Insufficient Balance");
        }

        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.toDto(savedAccount);
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));

        accountRepository.deleteById(id);
    }
}
