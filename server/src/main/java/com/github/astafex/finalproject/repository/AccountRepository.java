package com.github.astafex.finalproject.repository;

import entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account getAccountByNumber(String number);
}
