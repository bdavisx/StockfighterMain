package com.tartner.stockfighter.trader.configuration

import org.junit.Test

import org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers.*;
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.math.BigDecimal

class StockfighterGameMasterClientFactoryTest {
    @Test
    fun startLevel_Basic_StartsLevel() {
        val factory = StockfighterGameMasterClientFactory()
        val client = factory.createService("https://www.stockfighter.io/gm",
            "4d05754c6ab729aae1ffb3858fc14da21c385b11")

        val startData = client.startLevel("first_steps")

        client.endLevel(startData.instanceId)
    }

}

