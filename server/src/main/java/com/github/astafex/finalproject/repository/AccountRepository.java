package com.github.astafex.finalproject.repository;

import com.github.astafex.finalproject.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> getAccountByNumber(String number);
}
