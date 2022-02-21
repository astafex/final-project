package com.github.astafex.finalproject.repository;

import com.github.astafex.finalproject.entity.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    Card getCardByNumber(String number);
}
