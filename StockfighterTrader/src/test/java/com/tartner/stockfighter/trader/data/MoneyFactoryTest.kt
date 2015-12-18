package com.tartner.stockfighter.trader.data

import org.junit.Test

import org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers.*;
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.math.BigDecimal

class MoneyFactoryTest {
    @Test
    fun fromApiMoney_SimpleNumber_ReturnsCorrectAmount() {
        val factory = MoneyFactory();
        val money = factory.from(1234);
        assertThat(money, equalTo(Money.of(CurrencyUnit.USD, BigDecimal("12.34"))))
    }

    @Test
    fun fromApiMoney_LargeNumber_ReturnsCorrectAmount() {
        val factory = MoneyFactory();
        val money = factory.from(1234567890);
        assertThat(money, equalTo(Money.of(CurrencyUnit.USD, BigDecimal("12345678.90"))))
    }
}