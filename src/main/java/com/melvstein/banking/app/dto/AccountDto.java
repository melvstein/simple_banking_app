package com.melvstein.banking.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
}
