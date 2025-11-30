package com.melvstein.banking.app.mapper;

import com.melvstein.banking.app.dto.AccountDto;
import com.melvstein.banking.app.entity.Account;

public class AccountMapper {

    public static Account toEntity(AccountDto dto) {
        if (dto == null) {
            return null;
        }

        return Account.builder()
                .id(dto.getId())
                .accountHolderName(dto.getAccountHolderName())
                .balance(dto.getBalance())
                .build();
    }

    public static AccountDto toDto(Account entity) {
        if (entity == null) {
            return null;
        }

        return AccountDto.builder()
                .id(entity.getId())
                .accountHolderName(entity.getAccountHolderName())
                .balance(entity.getBalance())
                .build();
    }
}
