package com.tartner.stockfighter.trader.data

import org.junit.Test

import org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers.*;
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.math.BigDecimal

class MoneyFactoryTest {
    @Test
    fun apiToMoney_SimpleNumber_ReturnsCorrectAmount() {
        val factory = MoneyFactory();
        val money = factory.apiToMoney(1234);
        assertThat(money, equalTo(Money.of(CurrencyUnit.USD, BigDecimal("12.34"))))
    }

    private val bigAmount: Int = 1234567890

    @Test
    fun apiToMoney_LargeNumber_ReturnsCorrectAmount() {
        val factory = MoneyFactory();
        val money = factory.apiToMoney(bigAmount)
        assertThat(money, equalTo(Money.of(CurrencyUnit.USD, BigDecimal("12345678.90"))))
    }

    @Test
    fun moneyToAPI_SimpleNumber_ReturnsCorrectAmount() {
        val factory = MoneyFactory();
        val money = factory.apiToMoney(bigAmount);
        val apiMoney: Int = factory.moneyToAPI(money)
        assertThat(apiMoney, equalTo(bigAmount))
    }
}