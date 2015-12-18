package com.tartner.stockfighter.trader.data

import com.tartner.stockfighter.trader.apis.main.QuoteTO
import org.junit.Test

import org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers.*;
import java.time.LocalDateTime

class QuoteFactoryTest {
    @Test
    fun from_SimpleQuote_ReturnsCorrectValues() {
        val factory = QuoteFactory(MoneyFactory());

        val apiQuote = QuoteTO("FAC", "OGEX", 5100, 392, 2748, 5125, 711, 2237, 5125, 52,
            LocalDateTime.parse("2015-07-13T05:38:17.33640392"),
            LocalDateTime.parse("2015-07-13T05:38:17.33640392"))
        val quote = factory.convertFrom(apiQuote)

        assertThat(quote.symbol, equalTo(Symbol("FAC")))
    }


}