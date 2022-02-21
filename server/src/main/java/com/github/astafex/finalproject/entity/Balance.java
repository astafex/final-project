package com.github.astafex.finalproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BALANCE")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    long id;

    @Column(name = "AMOUNT", nullable = false)
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY", nullable = false)
    Currency currency;

    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    Account account;
}
