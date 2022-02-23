package com.github.astafex.finalproject.service;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.entity.Account;
import com.github.astafex.finalproject.entity.Balance;
import com.github.astafex.finalproject.entity.Card;
import com.github.astafex.finalproject.entity.Currency;
import com.github.astafex.finalproject.exception.CardCheckException;
import com.github.astafex.finalproject.exception.CardNotFoundException;
import com.github.astafex.finalproject.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CardServiceTest {
    @Test
    void getBalance_successTest() {
        Balance balanceMock = new Balance();
        balanceMock.setAmount(BigDecimal.valueOf(11111.11));
        balanceMock.setCurrency(Currency.RUB);

        Account accountMock = new Account();
        accountMock.setBalance(balanceMock);

        Card mockCard = new Card();
        mockCard.setNumber("1111111111111111");
        mockCard.setBlocked(false);
        mockCard.setExpirationDate(LocalDate.now());
        mockCard.setPIN(1111);
        mockCard.setAccount(accountMock);

        CardRepository cardRepository = Mockito.mock(CardRepository.class);
        when(cardRepository.getCardByNumber("1111111111111111")).thenReturn(Optional.of(mockCard));

        CardService cardService = new CardService(cardRepository);

        BalanceDto balanceExpect = new BalanceDto(BigDecimal.valueOf(11111.11), "RUB");
        BalanceDto balanceProvide = cardService.getBalance("1111111111111111", 1111);

        assertThat(balanceExpect).isEqualTo(balanceProvide);
    }

    @Test
    void getBalance_cardPinIncorrectTest() {
        Balance balanceMock = new Balance();
        balanceMock.setAmount(BigDecimal.valueOf(11111.11));
        balanceMock.setCurrency(Currency.RUB);

        Account accountMock = new Account();
        accountMock.setBalance(balanceMock);

        Card mockCard = new Card();
        mockCard.setNumber("1111111111111111");
        mockCard.setBlocked(false);
        mockCard.setExpirationDate(LocalDate.now());
        mockCard.setPIN(1111);
        mockCard.setAccount(accountMock);

        CardRepository cardRepository = Mockito.mock(CardRepository.class);
        when(cardRepository.getCardByNumber("1111111111111111")).thenReturn(Optional.of(mockCard));

        CardService cardService = new CardService(cardRepository);

        Exception exception = assertThrows(CardCheckException.class, () ->
                cardService.getBalance("1111111111111111", 2222));
        String messageExpect = "Введен неверный PIN";
        String messageProvide = exception.getMessage();

        assertThat(messageExpect).isEqualTo(messageProvide);
    }

    @Test
    void getBalance_cardDateExpireTest() {
        Balance balanceMock = new Balance();
        balanceMock.setAmount(BigDecimal.valueOf(11111.11));
        balanceMock.setCurrency(Currency.RUB);

        Account accountMock = new Account();
        accountMock.setBalance(balanceMock);

        Card mockCard = new Card();
        mockCard.setNumber("1111111111111111");
        mockCard.setBlocked(false);
        mockCard.setExpirationDate(LocalDate.now().minus(1L, ChronoUnit.DAYS));
        mockCard.setPIN(1111);
        mockCard.setAccount(accountMock);

        CardRepository cardRepository = Mockito.mock(CardRepository.class);
        when(cardRepository.getCardByNumber("1111111111111111")).thenReturn(Optional.of(mockCard));

        CardService cardService = new CardService(cardRepository);

        Exception exception = assertThrows(CardCheckException.class, () ->
                cardService.getBalance("1111111111111111", 1111));
        String messageExpect = "Срок действия карты истек";
        String messageProvide = exception.getMessage();

        assertThat(messageExpect).isEqualTo(messageProvide);
    }

    @Test
    void getBalance_cardIsBlockedTest() {
        Balance balanceMock = new Balance();
        balanceMock.setAmount(BigDecimal.valueOf(11111.11));
        balanceMock.setCurrency(Currency.RUB);

        Account accountMock = new Account();
        accountMock.setBalance(balanceMock);

        Card mockCard = new Card();
        mockCard.setNumber("1111111111111111");
        mockCard.setBlocked(true);
        mockCard.setExpirationDate(LocalDate.now());
        mockCard.setPIN(1111);
        mockCard.setAccount(accountMock);

        CardRepository cardRepository = Mockito.mock(CardRepository.class);
        when(cardRepository.getCardByNumber("1111111111111111")).thenReturn(Optional.of(mockCard));

        CardService cardService = new CardService(cardRepository);

        Exception exception = assertThrows(CardCheckException.class, () ->
                cardService.getBalance("1111111111111111", 1111));
        String messageExpect = "Карта заблокирована";
        String messageProvide = exception.getMessage();

        assertThat(messageExpect).isEqualTo(messageProvide);
    }

    @Test
    void getBalance_cardNotFoundTest() {
        CardRepository cardRepository = Mockito.mock(CardRepository.class);
        when(cardRepository.getCardByNumber("9999999999999999")).thenThrow(new CardNotFoundException("Карта не найдена"));

        CardService cardService = new CardService(cardRepository);

        Exception exception = assertThrows(CardNotFoundException.class, () ->
                cardService.getBalance("9999999999999999", 9999));
        String messageExpect = "Карта не найдена";
        String messageProvide = exception.getMessage();
        assertThat(messageExpect).isEqualTo(messageProvide);
    }
}