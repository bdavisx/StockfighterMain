package com.tartner.stockfighter.trader.configuration

import org.junit.Test

import org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers.*;
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.math.BigDecimal

class StockfighterTraderClientFactoryTest {
    @Test
    fun fromApiMoney_SimpleNumber_ReturnsCorrectAmount() {
        val factory = StockfighterTraderClientFactory()
        val client = factory.createService("https://api.stockfighter.io/ob/api")

        val quote = client.getQuoteForStock("OGEX", "FAC")

    }

}