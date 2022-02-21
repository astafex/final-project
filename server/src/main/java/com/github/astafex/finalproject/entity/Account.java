package com.github.astafex.finalproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    long id;

    @Column(name = "NUMBER_ACCOUNT", unique = true, nullable = false)
    String number;

    @OneToOne(mappedBy = "account")
    Balance balance;

    @OneToMany(mappedBy = "account")
    Set<Card> cards;

    public Collection<Card> getCards() {
        return Collections.unmodifiableCollection(cards);
    }
}