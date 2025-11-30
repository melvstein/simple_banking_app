package com.melvstein.banking.app.controller;

import com.melvstein.banking.app.dto.AccountDto;
import com.melvstein.banking.app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccount = accountService.createAccount(accountDto);
        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        List<AccountDto> accounts = accountService.getAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto account = accountService.getAccountById(id);

        if (account == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(account);
        }

        return ResponseEntity.ok(account);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(
            @PathVariable Long id,
            @RequestBody Map<String, Double> request
    ) {
        AccountDto accountDeposited = accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok(accountDeposited);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(
            @PathVariable Long id,
            @RequestBody Map<String, Double> request
    ) {
        AccountDto accountDeposited = accountService.withdraw(id, request.get("amount"));
        return ResponseEntity.ok(accountDeposited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable Long id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }
}
