package com.github.astafex.finalproject.service;

import com.github.astafex.finalproject.dto.AccountDto;
import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.entity.Account;
import com.github.astafex.finalproject.entity.Balance;
import com.github.astafex.finalproject.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;

    public BalanceDto getBalance(AccountDto accountDto) {
        Account account = accountRepository.getAccountByNumber(accountDto.getNumber());
        Balance balance = account.getBalance();
        return new BalanceDto(
                balance.getAmount(),
                balance.getCurrency().name()
        );
    }
}
